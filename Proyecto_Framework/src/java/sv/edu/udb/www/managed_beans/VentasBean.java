/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import sv.edu.udb.www.entities.*;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class VentasBean {

    private VentasModel modelo = new VentasModel();
    private MusicModel modelo2 = new MusicModel();
    private VentasEntity venta;
    private MusicEntity music;
    private EmpleadosEntity empleado;
    private List<VentasEntity> ventas;
    private List<VentasEntity> historia;

    public VentasBean() {
        venta = new VentasEntity();
        music = new MusicEntity();
        empleado = new EmpleadosEntity();
    }

    public VentasEntity getVentas() {
        return venta;
    }

    public void setVentas(VentasEntity venta) {
        this.venta = venta;
    }

    public List<VentasEntity> getVentass() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarVentas();
    }

    public String guardarVenta(int idVenta) {
        if (modelo.obtenerVentas1(idVenta) == 1) {

            if (modelo.modificarVentas(venta) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarVentas(venta) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String eliminarVenta() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet por ID
        String idVenta = JsfUtil.getRequest().getParameter("idVenta");

        if (modelo.eliminarVentas(Integer.parseInt(idVenta)) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return null;
    }

    public void obtenerVenta() {
        String idVenta = JsfUtil.getRequest().getParameter("idVenta");
        venta = modelo.obtenerVentas(Integer.parseInt(idVenta));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    public String guardarVenta2() {
        String id = JsfUtil.getRequest().getParameter("idMusic");
        String Usuario = JsfUtil.getRequest().getParameter("codigo");
        String Mail = JsfUtil.getRequest().getParameter("correo");
        String precio = JsfUtil.getRequest().getParameter("precio");
        String img = JsfUtil.getRequest().getParameter("img");
        String cancion = JsfUtil.getRequest().getParameter("nombre");
        String user = JsfUtil.getRequest().getParameter("user");
        // date
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        // date
        music.setIdMusic(Integer.parseInt(id));
        empleado.setCodigoEmpleado(Integer.parseInt(Usuario));
        venta.setIdMusic(music);
        venta.setIdUser(empleado);
        venta.setFechaVenta(strDate);
        if (modelo.insertarVentas(venta) != 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Algo salio mal, intente de nuevo"));

            return null;//Regreso a la misma página
        } else {
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
                mimeMessage.setFrom(new InternetAddress(correoEnvia, "Empresa Universal Music"));
                InternetAddress[] internetAddresses = {new InternetAddress(Mail)};
                mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
                mimeMessage.setSubject("Contraseña");
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setText("Estimad@ Usuario:" + user + " , Gracias por su compra\n"
                        + "Los detalles de su compra son: \n" + "Árticulo comprado: " + cancion + "\n" + "Precio: " 
                        + precio);
                MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
                mimeBodyPartAdjunto.attachFile(img);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                mimeMessage.setContent(multipart);
                javax.mail.Transport transport = session.getTransport("smtp");
                transport.connect(correoEnvia, claveCorreo);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

                transport.close();

            } catch (UnsupportedEncodingException | MessagingException ex) {
                aviso = 1;
            } catch (IOException ex) {
                Logger.getLogger(VentasBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success", "Gracias por su compra"));
            return null;
        }
    }

    /**
     * @return the music
     */
    public MusicEntity getMusic() {
        return music;
    }

    /**
     * @param music the music to set
     */
    public void setMusic(MusicEntity music) {
        this.music = music;
    }

    /**
     * @return the empleado
     */
    public EmpleadosEntity getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(EmpleadosEntity empleado) {
        this.empleado = empleado;
    }

    public void historial() {
        //Cambiar carnet a ID
        String usuario = JsfUtil.getRequest().getParameter("code");
        historia = modelo.historial(usuario);
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(List<VentasEntity> ventas) {
        this.ventas = ventas;
    }

    /**
     * @return the historia
     */
    public List<VentasEntity> getHistoria() {
        return historia;
    }

    /**
     * @param historia the historia to set
     */
    public void setHistoria(List<VentasEntity> historia) {
        this.historia = historia;
    }
}
