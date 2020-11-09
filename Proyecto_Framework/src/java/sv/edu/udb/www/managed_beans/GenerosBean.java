/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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

    private GenerosModel modelo = new GenerosModel();
    private GenerosEntity genero;
    private List<GenerosEntity> generos;

    public GenerosBean() {
        genero = new GenerosEntity();
    }

    public GenerosEntity getGenero() {
        return genero;
    }

    public void setGenero(GenerosEntity genero) {
        this.genero = genero;
    }

    public List<GenerosEntity> getListaGeneros() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarGategorias();
    }

    public String guardarGenero(int id) {
        if (modelo.obtenerGenero1(id) == 1) {

            if (modelo.modificarGeneros(genero) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Genero Guardado "));

                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarGeneros(genero) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Genero Guardado "));

                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String eliminarEstudiante() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet a ID
        String id = JsfUtil.getRequest().getParameter("id");
        if (modelo.eliminarEstudiante(Integer.parseInt(id)) > 0) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Genero Eliminado "));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "El Genero no se pudo eliminar "));
        }
        return null;

    }

    public void obtenerEstudiantes() {
        String id = JsfUtil.getRequest().getParameter("id");
        genero = modelo.obtenerGenero(Integer.parseInt(id));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

}
