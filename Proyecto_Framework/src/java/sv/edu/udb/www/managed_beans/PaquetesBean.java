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
    private List<PaquetesEntity> paquetes;
    
     public PaquetesBean() {
        paquete = new PaquetesEntity();
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
    
    public String guardarPaquete(String idPaquete) {
        if (modelo.obtenerPaquetes1(idPaquete) == 1) {

            if (modelo.modificarEmpleados(paquete) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        } else {

            if (modelo.insertarPaquete(paquete) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        }
    }
    
    public String eliminarPaquete() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet
        String carnet = JsfUtil.getRequest().getParameter("carnet");

        if (modelo.eliminarEmpleados(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";
    }
    
    public void obtenerPaquete() {
        String carnet = JsfUtil.getRequest().getParameter("carnet");
        paquete = modelo.obtenerPaquetes(carnet);
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

}
