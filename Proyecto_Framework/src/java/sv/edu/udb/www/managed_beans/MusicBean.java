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
import sv.edu.udb.www.entities.MusicEntity;
import sv.edu.udb.www.model.MusicModel;
import sv.edu.udb.www.utils.JsfUtil;
import org.primefaces.model.file.UploadedFile;
import sv.edu.udb.www.entities.EmpleadosEntity;
import sv.edu.udb.www.model.EmpleadosModel;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class MusicBean {

    private MusicModel modelo = new MusicModel();
    private EmpleadosModel modelo2 = new EmpleadosModel();
    private MusicEntity song;
    private GenerosEntity genero;
    private EmpleadosEntity empleados;
    private List<GenerosEntity> generos;
    private List<EmpleadosEntity>  empleado;
    private List<MusicEntity> music;
    private int operacion;
    private UploadedFile file;
    private UploadedFile canciones;

    public MusicEntity getMusic() {
        return song;
    }

    public void setMusic(MusicEntity cancion) {
        this.song = cancion;
    }

    public MusicBean() {
        song = new MusicEntity();
        genero = new GenerosEntity();
        empleados = new  EmpleadosEntity();
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the canciones
     */
    public UploadedFile getCanciones() {
        return canciones;
    }

    /**
     * @param canciones the canciones to set
     */
    public void setCanciones(UploadedFile canciones) {
        this.canciones = canciones;
    }

    /**
     *
     * @return
     */
    public List<MusicEntity> getTopMusica() {

        return modelo.topCanciones();
    }

    public List<MusicEntity> getListaMusica() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarCanciones();
    }

    public String obtenerLike(int id) {
        try {
            operacion = modelo.obtenerLike(id);
            modelo.darLike(id, operacion);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Gracias por votar por tú canción favorita"));
            return null;
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Algo ha sucedo mal por favor intenta de nuevo"));
            System.out.println(e);
            return null;
        }

    }

    public String guardarMusica(int id) {
        song.setId(genero);
        String archivo;
        String nombreCancion;
        nombreCancion = canciones.getFileName();
        archivo = file.getFileName();
        song.setLyrics("mp3/" + nombreCancion);
        song.setImagen("img/" + archivo);
        song.setLikes(0);
        if (modelo.obtenerCancion1(id) == 1) {

            if (modelo.modificarCanciones(song) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "No se pudo ingresar la canción"));
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Canción registrada exitosamente");
                //Forzando la redirección en el cliente
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Canción ingresada correctamente"));
                return null;
            }
        } else {

            if (modelo.insertarCancion(song) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                if (file != null) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info", "Canción guardada " + archivo));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Error al  guardar " + archivo));
                }

                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String eliminarMusica() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet por ID
        String id = JsfUtil.getRequest().getParameter("id");

        if (modelo.eliminarCanciones(Integer.parseInt(id)) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success", "Canción elimina correctamente"));
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Error", "La canción no se pudo eliminar por favor intenta de nuevo"));
        }
        return null;
    }

    public void obtenerMusica() {
        String id = JsfUtil.getRequest().getParameter("id");
        song = modelo.obtenerCancion(Integer.parseInt(id));
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
    }
     public String obtenerMusica2() {
        String id = JsfUtil.getRequest().getParameter("codigo");
        song = modelo.obtenerMusica(Integer.parseInt(id));
        String code = JsfUtil.getRequest().getParameter("code");
        empleados = modelo2.obtenerUser(code);
        String NombreUsuario=modelo2.obtenerUser(code).getNombreEmpleado();
        System.out.println(NombreUsuario);
        System.out.println(code);
        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        return "/faces/ComprarMusica";
    }
     

    /**
     * @return the operacion
     */
    public int getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    /**
     *
     * @return
     */
    public List<MusicEntity> listarSong() {
        String id = JsfUtil.getRequest().getParameter("id");
        //System.out.println(modelo.ListadoMusica(Integer.parseInt(id)));
        List<MusicEntity> lista = null;
        lista.add((MusicEntity) modelo.ListadoMusica(Integer.parseInt(id)));
        getMostrar();
        return lista;
    }

    public List<MusicEntity> getMostrar() {
        try {
            //  listarSong();
            MusicEntity music1 = (MusicEntity) listarSong();
            List<MusicEntity> lista = null;
            lista.add(music1);
            System.out.println("Nombres: " + music1.getNombreCancion());
            // FacesContext.getCurrentInstance().getExternalContext().redirect("faces/musicSelect.xhtml");
            return lista;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

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
    
    //Creacion de PDF de musica
    
    public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException{
		//Map<String,Object> parametros= new HashMap<String,Object>();
		//parametros.put("txtUsuario", "MitoCode");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/PDFmusica.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getListaMusica()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ListaMusica.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
    
    //Creación de Excel de Musica
    
    public void exportarExcel(ActionEvent actionEvent) throws JRException, IOException{
		
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Excelmusica.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getListaMusica()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=ListaMusica.xls");
		ServletOutputStream stream = response.getOutputStream();
		
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		exporter.exportReport();
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

    /**
     * @return the modelo2
     */
    public EmpleadosModel getModelo2() {
        return modelo2;
    }

    /**
     * @param modelo2 the modelo2 to set
     */
    public void setModelo2(EmpleadosModel modelo2) {
        this.modelo2 = modelo2;
    }

    /**
     * @return the empleados
     */
    public EmpleadosEntity getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(EmpleadosEntity empleados) {
        this.empleados = empleados;
    }

    /**
     * @return the empleado
     */
    public List<EmpleadosEntity> getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(List<EmpleadosEntity> empleado) {
        this.empleado = empleado;
    }

    /**
     * @param music the music to set
     */
    public void setMusic(List<MusicEntity> music) {
        this.music = music;
    }
}
