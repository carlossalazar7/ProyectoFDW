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
    /**
     * @return the genero
     */
    public PaquetesEntity getPaquete() {
        return paquete;
    }

    /**
     * @param genero the genero to set
     */
    public void setPaquete(PaquetesEntity paquete) {
        this.paquete = paquete;
    }


    private PaquetesModel modelo = new PaquetesModel();
    private PaquetesEntity paquete;
    private List<PaquetesEntity> paquetes;

    public PaquetesBean() {
        paquete = new PaquetesEntity();
        System.out.println(paquete.getNombrePaquete());
    }
    
    /**
     *
     * @return
     */
    public List<PaquetesEntity> getListaPaquetes() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarPaquetes();
    }

    public String guardarPaquete() {
        if (modelo.insertarPaquete(getPaquete()) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró un paquete con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "paquete registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }
}
