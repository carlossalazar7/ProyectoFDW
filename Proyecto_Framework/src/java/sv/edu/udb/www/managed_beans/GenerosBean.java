/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import sv.edu.udb.www.entities.GenerosEntity;
import sv.edu.udb.www.model.GenerosModel;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class GenerosBean {

    private GenerosModel modelo = new GenerosModel();
    private GenerosEntity genero;
    private List<GenerosEntity> generos;

    public GenerosBean() {
        genero = new GenerosEntity();
    }

    public GenerosEntity getGenero() {
        return genero;
    }

    public void setGenero(GenerosEntity genero) {
        this.genero = genero;
    }

    public List<GenerosEntity> getListaGeneros() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarGategorias();
    }

    public String guardarGenero(int id) {
        if (modelo.obtenerGenero1(id) == 1) {

            if (modelo.modificarGeneros(genero) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Genero Guardado "));

                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarGeneros(genero) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Genero Guardado "));

                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String eliminarEstudiante() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet a ID
        String id = JsfUtil.getRequest().getParameter("id");
        if (modelo.eliminarEstudiante(Integer.parseInt(id)) > 0) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Info", "Genero Eliminado "));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "El Genero no se pudo eliminar "));
        }
        return null;

    }

    public void obtenerEstudiantes() {
        String id = JsfUtil.getRequest().getParameter("id");
        genero = modelo.obtenerGenero(Integer.parseInt(id));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }
    
    //Creacion de PDF de musica
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException{
		//Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("txtUsuario", "MitoCode");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Generos.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getListaGeneros()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ListaGeneros.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
    
    //Creación de Excel de Musica
    
    public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException{
		
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Excelgeneros.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getListaGeneros()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ListaGeneros.xls");
		ServletOutputStream stream = response.getOutputStream();
		
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

}
