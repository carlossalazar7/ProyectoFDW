package sv.edu.udb.www.managed_beans;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.primefaces.model.file.UploadedFile;
import sv.edu.udb.www.entities.*;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class EmpleadosBean {

    private EmpleadosModel modelo = new EmpleadosModel();
    private MusicModel modelo2 = new MusicModel();
    private EmpleadosEntity empleado;
    private VentasEntity venta;
    private PlaylistEntity play;
    private TipoempleadosEntity tipoempleado;
    private List<TipoempleadosEntity> tipoempleados;
    private List<EmpleadosEntity> empleados;
    private List<PlaylistEntity> plays;
    private List<VentasEntity> ventas;
    private EntityManager manager;
    private EntityManager em;
    private UploadedFile file;

    public EmpleadosBean() {
        empleado = new EmpleadosEntity();
        tipoempleado = new TipoempleadosEntity();
        venta = new VentasEntity();
        play = new PlaylistEntity();
    }

    public EmpleadosEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadosEntity empleado) {
        this.empleado = empleado;
    }

    public List<EmpleadosEntity> getListaEmpleados() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarEmpleados();
    }

    public void verificar() {
        try {
            String nombre;
            nombre = modelo.iniciarSesion(empleado).getUsuarioEmpleado();
            nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin");
            //    FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

            if (nombre == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhml");
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public String guardarEmpleado(int codigo) {
        empleado.setCodigoTipoEmpleado(tipoempleado);
        if (modelo.obtenerEmpleados1(codigo) == 1) {

            if (modelo.modificarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "No se pudo modificar el usuario"));
                return null;//Regreso a la misma página
            } else {
                //  JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Usuario Agregado correctamente "));
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "No se pudo guardar el usuario"));
                return null;//Regreso a la misma página
            } else {
                // JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Usuario Guardado "));
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String guardarEmpleado2(int codigo) {
        int dato = 3;
        tipoempleado.setCodigoTipoEmpleado(dato);
        empleado.setCodigoTipoEmpleado(tipoempleado);
        if (modelo.obtenerEmpleados1(codigo) == 1) {

            if (modelo.modificarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                Correo();
                return null;
            }
        } else {

            if (modelo.insertarEmpleados(empleado) != 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "No se pudo crear su usuario, por favor intenta con otro correo"));
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Su cuenta fue creado correctamente, hemos enviado a su correo los detalles de su cuenta"));
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                Correo();
                return null;
            }
        }
    }

    public String eliminarEmpleado() {
        // Leyendo el parametro enviado desde la vista
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        if (modelo.eliminarEmpleado(Integer.parseInt(codigo)) > 0) {
            //  JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success", "Usuario eliminado "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No se pudo eliminar el usuario"));
            //JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return null;
    }

    public void modificarContrasena() {
        modelo.modificarContrasenas(empleado);
        JsfUtil.setFlashMessage("Éxito", "Contraseña modificada exitosamente");
        System.out.println("Se cambio");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Success", "Contraseña Modificada correctamente "));

    }

    public void modificarImagen() {
        String archivo;
        archivo = file.getFileName();
        empleado.setImagen("img/" + archivo);
        if (file != null) {
            JsfUtil.setFlashMessage("Éxito", "Imagen modificada exitosamente");
            System.out.println("Se cambio");
            modelo.modificarImagen(empleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success", "Contraseña Modificada correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Error", "No se pudo guardar"));
        }
    }

    public String recuperarContrasena() {
        try {
            if (modelo.recuperarContrasenas(empleado) != null) {
                JsfUtil.setFlashMessage("Éxito", "Contraseña modificada exitosamente");
                System.out.println("Se cambio");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "No se pudo encontrar ningún usuario con ese correo, por favor intente nuevamente"));
                return null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Su nueva contraseña fue enviada a su correo"));

                return null;
            }

        } catch (Exception e) {
            System.out.println(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Algo sucedio mal, por favor intente de nuevo"));
            return null;
        }
    }

    public void obtenerEmpleados() {
        //Cambiar carnet a ID
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        empleado = modelo.obtenerEmpleados(Integer.parseInt(codigo));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    public void obtenerEmpleados2() {
        //Cambiar carnet a ID
        String usuario = JsfUtil.getRequest().getParameter("code");
        int NombreUsuario = modelo.obtenerUser(usuario).getCodigoEmpleado();
        String id = Integer.toString(NombreUsuario);
        ventas = modelo2.listarCancionPorUsuario(NombreUsuario);
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    public void salir(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(EmpleadosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String filtrar2() {
        int numeroGenero = Integer.parseInt(JsfUtil.getRequest().getParameter("id"));
        System.out.println("Si llega el id");
        System.out.println(numeroGenero);
        System.out.println("Si llegó al Bean y el numero es: " + numeroGenero);
        plays = modelo2.ListadoPorPlaylist(numeroGenero);
        System.out.println("Devolucion: " + modelo2.ListadoPorPlaylist(numeroGenero));

        return "/faces/musicPlaylist";
    }

    public String obtenerEmpleados3() {
        //Cambiar carnet a ID
        String usuario = JsfUtil.getRequest().getParameter("code");
        int NombreUsuario = modelo.obtenerUser(usuario).getCodigoEmpleado();
        String id = Integer.toString(NombreUsuario);
        System.out.println("Este es el usuario ");
        System.out.println(usuario);
        System.out.println("Este es el id usuario ");
        System.out.println(id);
        return "/faces/crearPlaylist2.xhtml";
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    public void obtenerEmpleados4() {
        //Cambiar carnet a ID
        String usuario = JsfUtil.getRequest().getParameter("code");
        int NombreUsuario = modelo.obtenerUser(usuario).getCodigoEmpleado();
        String id = Integer.toString(NombreUsuario);
        System.out.println("Este es el usuario ");
        System.out.println(usuario);
        System.out.println("Este es el id usuario ");
        System.out.println(id);
        plays = modelo2.listarPlayListPorUsuario(NombreUsuario);
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (modelo.iniciarSesion(empleado) != null) {
            if (modelo.iniciarSesion(empleado).getCodigoTipoEmpleado().getCodigoTipoEmpleado() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Contraseña o usuario incorrecto "));
            } else {
                String nombre = modelo.iniciarSesion(empleado).getUsuarioEmpleado();
                String fullName = modelo.iniciarSesion(empleado).getNombreEmpleado();
                int id = modelo.iniciarSesion(empleado).getCodigoEmpleado();
                String imagen = modelo.iniciarSesion(empleado).getImagen();
                String correo = modelo.iniciarSesion(empleado).getCorreo();
                int codigo = modelo.iniciarSesion(empleado).getCodigoTipoEmpleado().getCodigoTipoEmpleado();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Datos correctos"));

                System.out.println(codigo);
                switch (codigo) {
                    case 1:
                        try {
                            HttpSession session = request.getSession();
                            session.setAttribute("Admin", nombre);
                            session.setAttribute("fullName", fullName);
                            session.setAttribute("codigo", id);
                            session.setAttribute("imagen", imagen);
                            session.setAttribute("correo", correo);
                            request.setAttribute("usuarioEmpleado", nombre);
                            request.setAttribute("codigoEmpleado", codigo);
                            request.setAttribute("fullName", fullName);
                            //request.getRequestDispatcher("faces/listado.xhtml").forward(request,response);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Admin", nombre);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/listado.xhtml");

                        } catch (IOException e) {
                            System.out.println(e);
                        }

                        break;
                    case 2:
                        try {
                            HttpSession session = request.getSession();
                            session.setAttribute("Editor", nombre);
                            session.setAttribute("fullName", fullName);
                            session.setAttribute("imagen", imagen);
                            session.setAttribute("correo", correo);
                            session.setAttribute("codigo", id);
                            request.setAttribute("usuarioEmpleado", nombre);
                            request.setAttribute("codigoEmpleado", codigo);
                            request.setAttribute("fullName", fullName);

                            //request.getRequestDispatcher("faces/paginaadmin2.xhtml").forward(request, response);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Editor", nombre);
                            //   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fullName", fullName);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginaadmin2.xhtml");

                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    case 3:
                        try {
                            HttpSession session = request.getSession();
                            session.setMaxInactiveInterval(10 * 60);
                            session.setAttribute("User", nombre);
                            session.setAttribute("fullName", fullName);
                            session.setAttribute("imagen", imagen);
                            session.setAttribute("correo", correo);
                            session.setAttribute("codigo", id);
                            request.setAttribute("usuarioEmpleado", nombre);
                            request.setAttribute("codigoEmpleado", codigo);
                            request.setAttribute("fullName", fullName);
                            //request.getRequestDispatcher("faces/paginausuario.xhtml").forward(request, response);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("User", nombre);
                            // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fullName", fullName);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginausuario.xhtml");
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Contraseña o usuario incorrecto "));
        }
    }

    /**
     * @return the tipoempleado
     */
    public TipoempleadosEntity getTipoempleado() {
        return tipoempleado;
    }

    /**
     * @param tipoempleado the tipoempleado to set
     */
    public void setTipoempleado(TipoempleadosEntity tipoempleado) {
        this.tipoempleado = tipoempleado;
    }

    /**
     * @return the tipoempleados
     */
    public List<TipoempleadosEntity> getTipoempleados() {
        return tipoempleados;
    }

    /**
     * @param tipoempleados the tipoempleados to set
     */
    public void setTipoempleados(List<TipoempleadosEntity> tipoempleados) {
        this.tipoempleados = tipoempleados;
    }

    public void Correo() {
        String correo = empleado.getCorreo();
        String nombre = empleado.getNombreEmpleado();
        String usuario = empleado.getUsuarioEmpleado();
        String password = empleado.getContrasena();
        // El correo gmail de envío
        String correoEnvia = "desafiodwf2020@gmail.com";
        String claveCorreo = "DESAFIO2020";

        // La configuración para enviar correo
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        Session session = Session.getInstance(properties, null);
        int aviso = 0;
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "Empresa"));
            InternetAddress[] internetAddresses = {new InternetAddress(correo)};
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
            mimeMessage.setSubject("Contraseña");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText("Estimad@ Usuario:" + nombre);
            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.setText("\nSu contraseña es: " + password);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(mimeBodyPart2);
            mimeMessage.setContent(multipart);
            javax.mail.Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            transport.close();

        } catch (UnsupportedEncodingException | MessagingException ex) {
            aviso = 1;
        }
    }

    public void Correo2() {
        String correo = empleado.getCorreo();
        String usuario = empleado.getUsuarioEmpleado();
        String password = empleado.getContrasena();
        // El correo gmail de envío
        String correoEnvia = "desafiodwf2020@gmail.com";
        String claveCorreo = "DESAFIO2020";

        // La configuración para enviar correo
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        Session session = Session.getInstance(properties, null);
        int aviso = 0;
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "Empresa"));
            InternetAddress[] internetAddresses = {new InternetAddress(correo)};
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
            mimeMessage.setSubject("Contraseña");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText("Estimad@ Usuario:" + usuario);
            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.setText("\nSu contraseña es: " + password);
            // Creo la parte del mensaje
            MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
            mimeBodyPartAdjunto.attachFile("C:/music.jpg");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(mimeBodyPart2);
            multipart.addBodyPart(mimeBodyPartAdjunto);
            mimeMessage.setContent(multipart);
            javax.mail.Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            transport.close();

        } catch (IOException | MessagingException ex) {
            aviso = 1;
        }
    }

    //Creacion de PDF de musica
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException {
        //Map<String,Object> parametros= new HashMap<String,Object>();
        //parametros.put("txtUsuario", "MitoCode");

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Empleados.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getListaEmpleados()));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListaEmpleados.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Creación de Excel de Musica
    public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException {

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Excelempleados.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getListaEmpleados()));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListaEmpleados.xls");
        ServletOutputStream stream = response.getOutputStream();

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * @return the venta
     */
    public VentasEntity getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(VentasEntity venta) {
        this.venta = venta;
    }

    /**
     * @return the ventas
     */
    public List<VentasEntity> getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(List<VentasEntity> ventas) {
        this.ventas = ventas;
    }

    /**
     * @return the play
     */
    public PlaylistEntity getPlay() {
        return play;
    }

    /**
     * @param play the play to set
     */
    public void setPlay(PlaylistEntity play) {
        this.play = play;
    }

    /**
     * @return the plays
     */
    public List<PlaylistEntity> getPlays() {
        return plays;
    }

    /**
     * @param plays the plays to set
     */
    public void setPlays(List<PlaylistEntity> plays) {
        this.plays = plays;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    public void timeOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("User");
        String user2 = (String) session.getAttribute("Admin");
        String user3 = (String) session.getAttribute("Editor");
        if(user == null  ){
             FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
        }
        if(user2 == null  ){
             FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
        }
        if(user3 == null  ){
             FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
        }
    }
}
