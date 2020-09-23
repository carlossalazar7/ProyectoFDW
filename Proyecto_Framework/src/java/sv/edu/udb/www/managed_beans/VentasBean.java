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
public class VentasBean {
     /**
     * @return the genero
     */
    public VentasEntity getVentas() {
        return venta;
    }

    /**
     * @param genero the genero to set
     */
    public void setVentas(VentasEntity venta) {
        this.venta = venta;
    }


    private VentasModel modelo = new VentasModel();
    private VentasEntity venta;
    private List<VentasEntity> ventas;

    public VentasBean() {
        venta = new VentasEntity();
        System.out.println(venta.getIdVenta());
    }
    
     /**
     *
     * @return
     */
    public List<VentasEntity> getVentass() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarVentas();
    }

    public String guardarVenta() {
        if (modelo.insertarVentas(getVentas()) != 1) {
            JsfUtil.setErrorMessage(null, "Ya se registró una venta con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "venta registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }
}
