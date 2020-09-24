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
public String guardarGenero(String generos) {
        if (modelo.obtenerGenero1(generos) == 1) {

            if (modelo.modificarGeneros(genero) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        } else {

            if (modelo.insertarGeneros(genero)!= 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        }
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
    public String eliminarEstudiante() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet a ID
        String carnet = JsfUtil.getRequest().getParameter("carnet");

        if (modelo.eliminarGenero(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";
    }
    public void obtenerEstudiantes() {
        String carnet = JsfUtil.getRequest().getParameter("carnet");
        genero = modelo.obtenerGenero(carnet);
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        
      
    }

}
