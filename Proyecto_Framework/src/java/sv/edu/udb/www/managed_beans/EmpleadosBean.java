
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

    public String guardarEmpleado(int codigo) {
        if (modelo.obtenerEmpleados1(codigo) == 1) {

            if (modelo.modificarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarEmpleados(empleado) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }
    
     public String eliminarEmpleado() {
        // Leyendo el parametro enviado desde la vista
        String codigo = JsfUtil.getRequest().getParameter("codigo");

        if (modelo.eliminarEmpleado(Integer.parseInt(codigo))> 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "listado?faces-redirect=true";
    }
    
    public void obtenerEmpleados() {
        //Cambiar carnet a ID
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        empleado = modelo.obtenerEmpleados(Integer.parseInt(codigo));
        
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        
      
    }
    

}
