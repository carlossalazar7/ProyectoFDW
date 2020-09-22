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
import sv.edu.udb.www.model.GenerosModel;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class GenerosBean {

    /**
     * @return the genero
     */
    public GenerosEntity getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(GenerosEntity genero) {
        this.genero = genero;
    }


    private GenerosModel modelo = new GenerosModel();
    private GenerosEntity genero;
    private List<GenerosEntity> generos;

    public GenerosBean() {
        genero = new GenerosEntity();
        System.out.println(genero.getNombreGenero());
    }

    /**
     *
     * @return
     */
    public List<GenerosEntity> getListaGeneros() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarGategorias();
    }

    public String guardarGenero() {
        if (modelo.insertarGeneros(getGenero()) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un genero con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "artista registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }

}
