package sv.edu.udb.www.managed_beans;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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

    public void verificar() {
        try {
            String nombre = modelo.iniciarSesion(empleado).getUsuarioEmpleado();
            nombre = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin");
            //    FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

            if (nombre == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhml");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

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

        if (modelo.eliminarEmpleado(Integer.parseInt(codigo)) > 0) {
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

    public void login() {
        if (modelo.iniciarSesion(empleado) != null) {
            if (modelo.iniciarSesion(empleado).getCodigoTipoEmpleado().getCodigoTipoEmpleado() == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Contraseña o usuario incorrecto "));
            } else {
                String nombre = modelo.iniciarSesion(empleado).getUsuarioEmpleado();
                int codigo = modelo.iniciarSesion(empleado).getCodigoTipoEmpleado().getCodigoTipoEmpleado();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Datos correctos"));

                System.out.println(codigo);
                try {
                    switch (codigo) {
                        case 1:
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", nombre);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginaadmin1.xhtml");
                            break;
                        case 2:
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", nombre);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginaadmin2.xhtml");
                            break;
                        case 3:
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", nombre);
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/paginausuario.xhtml");
                            break;
                        default:
                            System.out.println("Error");
                            break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(EmpleadosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Contraseña o usuario incorrecto "));
        }
    }
}