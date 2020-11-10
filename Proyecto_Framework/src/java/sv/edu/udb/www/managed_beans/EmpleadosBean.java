package sv.edu.udb.www.managed_beans;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import sun.rmi.transport.Transport;
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
    private EmpleadosEntity empleado;
    private TipoempleadosEntity tipoempleado;
    private List<TipoempleadosEntity> tipoempleados;
    private List<EmpleadosEntity> empleados;
    private EntityManager manager;
    private EntityManager em;

    public EmpleadosBean() {
        empleado = new EmpleadosEntity();
        tipoempleado = new TipoempleadosEntity();
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
            String nombre = modelo.iniciarSesion(empleado).getUsuarioEmpleado();
            nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin");
            //    FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

            if (nombre == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhml");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String guardarEmpleado(int codigo) {
        empleado.setCodigoTipoEmpleado(tipoempleado);
        if (modelo.obtenerEmpleados1(codigo) == 1) {

            if (modelo.modificarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "listado?faces-redirect=true";
            }
        } else {

            if (modelo.insertarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "listado?faces-redirect=true";
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
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
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
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "listado?faces-redirect=true";
    }

    public void modificarContrasena() {
        modelo.modificarContrasenas(empleado);
                JsfUtil.setFlashMessage("Éxito", "Contraseña modificada exitosamente");
                System.out.println("Se cambio");
           
    }
    public void recuperarContrasena() {
        modelo.recuperarContrasenas(empleado);
                JsfUtil.setFlashMessage("Éxito", "Contraseña modificada exitosamente");
                System.out.println("Se cambio");           
    }

    public void obtenerEmpleados() {
        //Cambiar carnet a ID
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        empleado = modelo.obtenerEmpleados(Integer.parseInt(codigo));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    public void login() {
        if (modelo.iniciarSesion(empleado) != null) {
            if (modelo.iniciarSesion(empleado).getCodigoTipoEmpleado().getCodigoTipoEmpleado() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Contraseña o usuario incorrecto "));
            } else {
                String nombre = modelo.iniciarSesion(empleado).getUsuarioEmpleado();
                int codigo = modelo.iniciarSesion(empleado).getCodigoTipoEmpleado().getCodigoTipoEmpleado();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Datos correctos"));

                System.out.println(codigo);
                try {
                    switch (codigo) {
                        case 1:
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", nombre);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginaadmin1.xhtml");
                            break;
                        case 2:
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", nombre);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginaadmin2.xhtml");
                            break;
                        case 3:
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", nombre);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginausuario.xhtml");
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(EmpleadosBean.class.getName()).log(Level.SEVERE, null, ex);
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

        } catch (Exception ex) {
            ex.printStackTrace();
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
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(mimeBodyPart2);
            mimeMessage.setContent(multipart);
            javax.mail.Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

            transport.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            aviso = 1;
        }
    }
}
