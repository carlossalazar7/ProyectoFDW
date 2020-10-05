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
 * @author Lenovo
 */@ManagedBean
@RequestScoped
public class NombreplaylistBean {
    
      private NombreplaylistModel modelo = new NombreplaylistModel();
    private NombreplaylistEntity nombreplaylist;
    private List<NombreplaylistEntity> playlist;
    
    public NombreplaylistBean() {
        nombreplaylist = new NombreplaylistEntity();
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

    public String guardarListaPlaylist(String idNombrePlayList) {
        if (modelo.obtenerPlayList1(idNombrePlayList) == 1) {

            if (modelo.modificarPlayList(nombreplaylist)!= 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "listadoNombrePlaylist?faces-redirect=true";
            }
        } else {

            if (modelo.insertarPlayList(nombreplaylist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "listadoNombrePlaylist?faces-redirect=true";
            }
        }
    }
    
    public String eliminarNombrePlaylist() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarPlayList(Integer.parseInt(id))> 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "listadoNombrePlaylist?faces-redirect=true";
    }
    
    public void obtenerNombrePlaylist() {
        String id = JsfUtil.getRequest().getParameter("id");
        nombreplaylist = modelo.obtenerPlayList(Integer.parseInt(id));
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        
      
    }
}
