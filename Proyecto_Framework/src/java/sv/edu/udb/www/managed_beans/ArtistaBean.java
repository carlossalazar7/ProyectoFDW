/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import sv.edu.udb.www.entities.*;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class ArtistaBean {

    /**
     * @return the artista
     */
    public ArtistaEntity getArtista() {
        return artista;
    }

    /**
     * @param artista the artista to set
     */
    public void setArtista(ArtistaEntity artista) {
        this.artista = artista;
    }

    ArtistaModel modelo = new ArtistaModel();
    private ArtistaEntity artista;
    private List<ArtistaEntity> listaArtista;

    public ArtistaBean() {
        artista = new ArtistaEntity();
        System.out.println(artista.getDescripcion());
    }

    public List<ArtistaEntity> getListaArtista() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarArtista();
    }

    public String guardarArtista() {
        if (modelo.insertarArtista(getArtista()) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un artista con este carnet ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "artista registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "artistas/registroArtista?faces-redirect=true";
        }
    }

}
