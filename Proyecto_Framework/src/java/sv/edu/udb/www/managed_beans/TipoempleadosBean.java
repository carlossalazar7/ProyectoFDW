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
     /**
     * @return the genero
     */
    public TipoempleadosEntity getTipoempleado() {
        return tipoempleado;
    }

    /**
     * @param genero the genero to set
     */
    public void setTipoempleado(TipoempleadosEntity tipoempleado) {
        this.tipoempleado = tipoempleado;
    }


    private TipoempleadosModel modelo = new TipoempleadosModel();
    private TipoempleadosEntity tipoempleado;
    private List<TipoempleadosEntity> tipoempleados;

    public TipoempleadosBean() {
        tipoempleado = new TipoempleadosEntity();
        System.out.println(tipoempleado.getNombreTipoEmpleado());
    }
    
     /**
     *
     * @return
     */
    
     public List<TipoempleadosEntity> getTipoEmpleados() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarTipoEmpleado();
    }

    public String guardarTipoEmpleado() {
        if (modelo.insertarTipoEmpleado(getTipoempleado()) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un empleado con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "empleado registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }
}
