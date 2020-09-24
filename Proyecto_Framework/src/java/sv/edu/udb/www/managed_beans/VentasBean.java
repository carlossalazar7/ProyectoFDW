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
     
     private VentasModel modelo = new VentasModel();
    private VentasEntity venta;
    private List<VentasEntity> ventas;

    public VentasBean() {
        venta = new VentasEntity();
    }
    
    public VentasEntity getVentas() {
        return venta;
    }

    public void setVentas(VentasEntity venta) {
        this.venta = venta;
    }

    public List<VentasEntity> getVentass() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarVentas();
    }

    public String guardarVenta(String idVenta) {
        if (modelo.obtenerVentas1(idVenta) == 1) {

            if (modelo.modificarVentas(venta) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        } else {

            if (modelo.insertarVentas(venta) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "registroEstudiantes?faces-redirect=true";
            }
        }
    }
    
    public String eliminarVenta() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet por ID
        String carnet = JsfUtil.getRequest().getParameter("carnet");

        if (modelo.eliminarVentas(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";
    }
    
     public void obtenerVenta() {
        String carnet = JsfUtil.getRequest().getParameter("carnet");
        venta = modelo.obtenerVentas(carnet);
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }
}
