/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sv.edu.udb.www.entities.GenerosEntity;
import sv.edu.udb.www.entities.MusicEntity;
import sv.edu.udb.www.model.MusicModel;
import sv.edu.udb.www.utils.JsfUtil;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@RequestScoped
public class MusicBean {

    private MusicModel modelo = new MusicModel();
    private MusicEntity song;
    private GenerosEntity genero;
    private List<GenerosEntity> generos;
    private List<MusicEntity> music;
    private int operacion;
    private UploadedFile file;

    public MusicEntity getMusic() {
        return song;
    }

    public void setMusic(MusicEntity cancion) {
        this.song = cancion;
    }

    public MusicBean() {
        song = new MusicEntity();
        genero = new GenerosEntity();
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
        operacion = modelo.obtenerLike(id);

        modelo.darLike(id, operacion);

        return null;

    }

    public String guardarMusica(int id) {
        song.setId(genero);
        String archivo;
        archivo = file.getFileName();
        String arch = file.toString();
        String archiv = file.getContentType();
        System.out.println(archivo+ "\n"+"\n"+ arch);
        song.setImagen("img/"+archivo);
        song.setLikes(0);
        if (modelo.obtenerCancion1(id) == 1) {

            if (modelo.modificarCanciones(song) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Canción registrada exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarCancion(song) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                if (file != null) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Info", "Canción guardada " + arch));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Error al  guardar " + arch));
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
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return null;
    }

    public void obtenerMusica() {
        String id = JsfUtil.getRequest().getParameter("id");
        song = modelo.obtenerCancion(Integer.parseInt(id));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
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
}
