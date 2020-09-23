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
    /**
     * @return the genero
     */
    public PlaylistEntity getPlayLista() {
        return playlist;
    }

    /**
     * @param genero the genero to set
     */
    public void setPlayLista(PlaylistEntity playlist) {
        this.playlist = playlist;
    }


    private PlaylistModel modelo = new PlaylistModel();
    private PlaylistEntity playlist;
    private List<PlaylistEntity> playlista;

    public PlaylistBean() {
        playlist = new PlaylistEntity();
        System.out.println(playlist.getIdNombrePlayList());
    }
    
     /**
     *
     * @return
     */
    public List<PlaylistEntity> getPlaylista() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarPlayList();
    }

    public String guardarPlayLista() {
        if (modelo.insertarPlayList(getPlayLista()) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un genero con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "artista registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }
}
