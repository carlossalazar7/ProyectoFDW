/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.udb.www.entities.*;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class PlaylistBean {
    
    private PlaylistModel modelo = new PlaylistModel();
    private PlaylistEntity playlist;
    private List<PlaylistEntity> playlista;

    public PlaylistBean() {
        playlist = new PlaylistEntity();
    }
    
    public PlaylistEntity getPlayLista() {
        return playlist;
    }

    public void setPlayLista(PlaylistEntity playlist) {
        this.playlist = playlist;
    }

    public List<PlaylistEntity> getPlaylista() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarPlayList();
    }

    public String guardarPlayLista(int idPlayList) {
        if (modelo.obtenerPlayList1(idPlayList) == 1) {

            if (modelo.modificarPlayList(playlist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarPlayList(playlist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }
    
    public String eliminarPlayLista() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet por ID
        String idPlayList = JsfUtil.getRequest().getParameter("idPlayList");

        if (modelo.eliminarEmpleados(Integer.parseInt(idPlayList)) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";
    }
    
    public void obtenerPlayLista() {
        String idPlayList = JsfUtil.getRequest().getParameter("idPlayList");
        playlist = modelo.obtenerPlayList(Integer.parseInt(idPlayList));
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }
}
