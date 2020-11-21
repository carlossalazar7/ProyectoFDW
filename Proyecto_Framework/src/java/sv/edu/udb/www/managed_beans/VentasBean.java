/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
