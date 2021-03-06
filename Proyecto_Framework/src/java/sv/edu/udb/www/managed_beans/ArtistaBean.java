/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import javafx.event.ActionEvent;
import javax.faces.application.FacesMessage;
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
import org.primefaces.model.file.UploadedFile;
import sv.edu.udb.www.entities.*;
import sv.edu.udb.www.model.*;
import sv.edu.udb.www.utils.JsfUtil;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class ArtistaBean {

    /**
     * @return the artista
     */
    public ArtistaEntity getArtista() {
        return artista;
    }

    /**
     * @param artista the artista to set
     */
    public void setArtista(ArtistaEntity artista) {
        this.artista = artista;
    }

    ArtistaModel modelo = new ArtistaModel();
    private ArtistaEntity artista;
    private List<ArtistaEntity> listaArtista;
    private UploadedFile img;
    private GenerosEntity genero;
    private List<GenerosEntity> generos;

    /**
     * @return the genero
     */
    public GenerosEntity getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(GenerosEntity genero) {
        this.genero = genero;
    }

    /**
     * @return the generos
     */
    public List<GenerosEntity> getGeneros() {
        return generos;
    }

    /**
     * @param generos the generos to set
     */
    public void setGeneros(List<GenerosEntity> generos) {
        this.generos = generos;
    }

    /**
     * @return the img
     */
    public UploadedFile getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public ArtistaBean() {
        artista = new ArtistaEntity();
        genero = new GenerosEntity();
        System.out.println(artista.getDescripcion());
    }

    public List<ArtistaEntity> getListaArtista() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarArtista();
    }

    public String guardarArtista(int id) {
        artista.setId(genero);
        String imagen;
        imagen = img.getFileName();
        artista.setNombreArtista("artistas/" + imagen);

        if (modelo.obtenerArtista1(id) == 1) {

            if (modelo.modificarArtista(artista) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Algo ha sucedo mal momente de modificar por favor intenta de nuevo"));
                return null;//Regreso a la misma página
            } else {
                //JsfUtil.setFlashMessage("exito", "Artista registrado exitosamente");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info", "Artista modificado correctamente "));
                
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarArtista(artista) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Algo ha sucedo mal momente de guardar por favor intenta de nuevo"));
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                System.out.println(modelo.insertarArtista(artista));
                if (img != null) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info", "Artista guardado " + imagen));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Error al  guardar " + imagen));
                }
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info", "Artista guardado correctamente "));
                return null;
            }
        }
    }

    public String eliminarArtista() {
        // Leyendo el parametro enviado desde la vista
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarArtista(Integer.parseInt(id)) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Artista eliminado corectamente"));
            
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Algo ha sucedo mal momente de eliminar por favor intenta de nuevo"));
        }
        return null;
    }

    public void obtenerArtista() {
        //Cambiar carnet a ID
        String id = JsfUtil.getRequest().getParameter("id");
        artista = modelo.obtenerArtista(Integer.parseInt(id));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }

    //Creacion de PDF de musica
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException{
		//Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("txtUsuario", "MitoCode");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Usuarios.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getListaArtista()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ListaArtistas.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
    
    //Creación de Excel de Musica
    
    public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException{
		
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/UsuariosExcel.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getListaArtista()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ListaArtistas.xls");
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
