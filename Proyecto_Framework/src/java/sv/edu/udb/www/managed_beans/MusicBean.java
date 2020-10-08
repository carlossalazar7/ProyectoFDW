/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.edu.udb.www.entities.GenerosEntity;
import sv.edu.udb.www.entities.MusicEntity;
import sv.edu.udb.www.model.MusicModel;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class MusicBean {
      private MusicModel modelo = new MusicModel();
    private MusicEntity song;
    private List<MusicEntity> music;
    
    public MusicEntity getMusic() {
        return song;
    }

    
    public void setMusic(MusicEntity cancion) {
        this.song = cancion;
    }

    public MusicBean() {
        song = new MusicEntity();
    }

    /**
     *
     * @return
     */
    public List<MusicEntity> getTopMusica(){
    
    return modelo.topCanciones();
    }
    
    public List<MusicEntity> getListaMusica() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarCanciones();
    }

    public String guardarMusica(int id) {
        if (modelo.obtenerCancion1(id)== 1) {

            if (modelo.modificarCanciones(song) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Canción registrada exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarCancion(song) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Canción registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }
    
    public String eliminarMusica() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet por ID
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarCanciones(Integer.parseInt(id)) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";
    }
    
    public void obtenerMusica() {
        String id = JsfUtil.getRequest().getParameter("id");
        song = modelo.obtenerCancion(Integer.parseInt(id));
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        
      
    }
}
