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
    /**
     * @return the genero
     */
    public NombreplaylistEntity getPlaylista() {
        return nombreplaylist;
    }

    /**
     * @param genero the genero to set
     */
    public void setPlaylista(NombreplaylistEntity nombreplaylist) {
        this.nombreplaylist = nombreplaylist;
    }


    private NombreplaylistModel modelo = new NombreplaylistModel();
    private NombreplaylistEntity nombreplaylist;
    private List<NombreplaylistEntity> playlist;
    
    /**
     *
     * @return
     */
    public List<NombreplaylistEntity> getListaPlaylist() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarPlayList();
    }

    public String guardarPlaylist() {
        if (modelo.insertarPlayList(getPlaylista()) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró una playlist con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "playlist registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }
}
