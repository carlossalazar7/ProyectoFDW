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
public class TipoempleadosBean {
     
    private TipoempleadosModel modelo = new TipoempleadosModel();
    private TipoempleadosEntity tipoempleado;
    private List<TipoempleadosEntity> tipoempleados;
    
    public TipoempleadosBean() {
        tipoempleado = new TipoempleadosEntity();
    }
    
    public TipoempleadosEntity getTipoempleado() {
        return tipoempleado;
    }

    public void setTipoempleado(TipoempleadosEntity tipoempleado) {
        this.tipoempleado = tipoempleado;
    }

    
     public List<TipoempleadosEntity> getTipoEmpleados() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarTipoEmpleado();
    }

    public String guardarTipoEmpleado(String codigoTipoEmpleado) {
        if (modelo.obtenerTipoEmpleado1(codigoTipoEmpleado) == 1) {

            if (modelo.modificarTipoEmpleado(tipoempleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        } else {

            if (modelo.insertarTipoEmpleado(tipoempleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        }
    }
    
    public String eliminarTipoEmpleado() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet por ID
        String carnet = JsfUtil.getRequest().getParameter("carnet");

        if (modelo.eliminarTipoEmpleado(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";
    }
    
    public void obtenerTipoEmpleado() {
        String carnet = JsfUtil.getRequest().getParameter("carnet");
        tipoempleado = modelo.obtenerTipoEmpleado(carnet);
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }
}
