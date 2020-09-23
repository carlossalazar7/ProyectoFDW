
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
    
    /**
     * @return the genero
     */
    public EmpleadosEntity getMusic() {
        return empleado;
    }

    /**
     * @param empleado the genero to set
     */
    public void setMusic(EmpleadosEntity empleado) {
        this.empleado = empleado;
    }
    private EmpleadosModel modelo = new EmpleadosModel();
    private EmpleadosEntity empleado;
    private List<EmpleadosEntity> empleados;
    
    public EmpleadosBean() {
        empleado = new EmpleadosEntity();
        System.out.println(empleado.getNombreEmpleado());
    }
    
     public List<EmpleadosEntity> getListaEmpleados() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarEmpleados();
    }

    public String guardarEmpleado() {
        if (modelo.insertarEmpleados(getMusic()) != 1){
            JsfUtil.setErrorMessage(null, "Ya se registró un Empleado con este id ");
            return null;//Regreso a la misma página
        } else {
            JsfUtil.setFlashMessage("exito", "Empleado registrado exitosamente ");
            //Forzando la redirección en el cliente
            return "index?faces-redirect=true";
        }
    }

}
