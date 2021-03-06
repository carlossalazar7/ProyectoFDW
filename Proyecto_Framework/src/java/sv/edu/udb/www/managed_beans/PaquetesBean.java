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
import sv.edu.udb.www.entities.PaquetesEntity;
import sv.edu.udb.www.model.PaquetesModel;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class PaquetesBean {

    private PaquetesModel modelo = new PaquetesModel();
    private PaquetesEntity paquete;
    private GenerosEntity genero;
    private List<GenerosEntity> generos;
    private List<PaquetesEntity> paquetes;

    public PaquetesBean() {
        paquete = new PaquetesEntity();
        genero = new GenerosEntity();
    }

    public PaquetesEntity getPaquete() {
        return paquete;
    }

    public void setPaquete(PaquetesEntity paquete) {
        this.paquete = paquete;
    }

    public List<PaquetesEntity> getListaPaquetes() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarPaquetes();
    }

    public String guardarPaquete(int id) {
        paquete.setId(genero);
        if (modelo.obtenerPaquetes1(id) == 1) {

            if (modelo.modificarEmpleados(paquete) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarPaquete(paquete) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String eliminarPaquete() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarEmpleados(Integer.parseInt(id)) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return null;
    }

    public String obtenerPaquete() {
        // String id = JsfUtil.getRequest().getParameter("id");
        //  paquete = modelo.obtenerPaquetes(Integer.parseInt(id));       
        //  System.out.println(id);
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        return "comprar?faces-redirect=true";
    }

    public String obtenerPaquete2() {
        String id = JsfUtil.getRequest().getParameter("id");
        paquete = modelo.obtenerPaquetes(Integer.parseInt(id));
        //  System.out.println(id);
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        return null;
    }

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

    /**
     * @return the generos
     */
    public List<GenerosEntity> getGeneros() {
        return generos;
    }

    /**
     * @param generos the generos to set
     */
    public void setGeneros(List<GenerosEntity> generos) {
        this.generos = generos;
    }

}
