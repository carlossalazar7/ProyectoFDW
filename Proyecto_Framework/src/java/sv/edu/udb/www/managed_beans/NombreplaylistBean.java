/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import sv.edu.udb.www.entities.*;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class NombreplaylistBean {

    private NombreplaylistModel modelo = new NombreplaylistModel();
    private PlaylistModel modelo1 = new PlaylistModel();
    private NombreplaylistEntity nombreplaylist;
    private List<NombreplaylistEntity> playlist;
    private EmpleadosEntity empleado;

    public NombreplaylistBean() {
        nombreplaylist = new NombreplaylistEntity();
        empleado = new EmpleadosEntity();
    }

    public NombreplaylistEntity getPlaylista() {
        return nombreplaylist;
    }

    public void setPlaylista(NombreplaylistEntity nombreplaylist) {
        this.nombreplaylist = nombreplaylist;
    }

    public List<NombreplaylistEntity> getListaPlaylist() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarPlayList();
    }

    public List<PlaylistEntity> Mylist(HttpServletRequest request) {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo1.Lista(request);
    }

    public String guardarListaPlaylist(int id) {
        if (modelo.obtenerPlayList1(id) == 1) {

            if (modelo.modificarPlayList(nombreplaylist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error", "Error al guardar la  play list "));
                return null;//Regreso a la misma página
            } else {
                //JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Play List guardada correctamente "));
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarPlayList(nombreplaylist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String guardarListaPlaylist2(int id) {
        if (modelo.obtenerPlayList1(id) == 1) {

            if (modelo.modificarPlayList(nombreplaylist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "crearPlayList?faces-redirect=true";
            }
        } else {

            if (modelo.insertarPlayList(nombreplaylist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "crearPlayList?faces-redirect=true";
            }
        }
    }

    public String eliminarNombrePlaylist() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarPlayList(Integer.parseInt(id)) > 0) {
            // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success", "Play List eliminada correctamente "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No se pudo eliminar "));
        }
        return null;
    }

    public void obtenerNombrePlaylist() {
        String id = JsfUtil.getRequest().getParameter("id");
        nombreplaylist = modelo.obtenerPlayList(Integer.parseInt(id));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    /**
     * @return the modelo1
     */
    public PlaylistModel getModelo1() {
        return modelo1;
    }

    /**
     * @param modelo1 the modelo1 to set
     */
    public void setModelo1(PlaylistModel modelo1) {
        this.modelo1 = modelo1;
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
}
