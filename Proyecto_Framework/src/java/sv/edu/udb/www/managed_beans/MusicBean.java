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
    /**
     * @return the genero
     */
    public MusicEntity getMusic() {
        return cancion;
    }

    /**
     * @param genero the genero to set
     */
    public void setMusic(MusicEntity cancion) {
        this.cancion = cancion;
    }


    private MusicModel modelo = new MusicModel();
    private MusicEntity cancion;
    private List<MusicEntity> music;
    
    
    public MusicBean() {
        cancion = new MusicEntity();
        System.out.println(cancion.getNombreCancion());
    }

    /**
     *
     * @return
     */
    public List<MusicEntity> getListaMusica() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarCanciones();
    }

    public String guardarMusica() {
        if (modelo.insertarCancion(getMusic()) != 1){
            JsfUtil.setErrorMessage(null, "Ya se registró una canción con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "cancion registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }
}
