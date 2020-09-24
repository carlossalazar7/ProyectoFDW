
package sv.edu.udb.www.managed_beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import sv.edu.udb.www.entities.*;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.utils.JsfUtil;
/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class EmpleadosBean {
    
    private EmpleadosModel modelo = new EmpleadosModel();
    private EmpleadosEntity empleado;
    private List<EmpleadosEntity> empleados;
    
    public EmpleadosBean() {
        empleado = new EmpleadosEntity();
    }
    
    public EmpleadosEntity getEmpleado() {
        return empleado;
    }

 
    public void setEmpleado(EmpleadosEntity empleado) {
        this.empleado = empleado;
    }
    
    
     public List<EmpleadosEntity> getListaEmpleados() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarEmpleados();
    }

    public String guardarEmpleado(String codigoEmpleado) {
        if (modelo.obtenerEmpleados1(codigoEmpleado) == 1) {

            if (modelo.insertarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "NewUser?faces-redirect=true";
            }
        } else {

            if (modelo.insertarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return "NewUser?faces-redirect=true";
            }
        }
    }
    
    public String eliminarEmpleados() {
        // Leyendo el parametro enviado desde la vista
        String carnet = JsfUtil.getRequest().getParameter("carnet");
//Cambiar carnet a id
        if (modelo.eliminarEmpleados(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "NewUser?faces-redirect=true";
    }
    
    public void obtenerEmpleados() {
        //Cambiar carnet a ID
        String carnet = JsfUtil.getRequest().getParameter("carnet");
        empleado = modelo.obtenerEmpleados(carnet);
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        
      
    }
    

}
