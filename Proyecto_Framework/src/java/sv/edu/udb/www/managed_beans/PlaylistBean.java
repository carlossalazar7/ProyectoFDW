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
public class PlaylistBean {

    private PlaylistModel modelo = new PlaylistModel();
    private EmpleadosModel modelo2 = new EmpleadosModel();
    private GenerosModel modelo3 = new GenerosModel();
    private EmpleadosModel modelo4 = new EmpleadosModel();
    private MusicEntity song;
    private NombreplaylistEntity nombre;
    private PlaylistEntity playlist;
    private GenerosEntity genero;
    private EmpleadosEntity empleados;
    private List<MusicEntity> songs;
    private List<NombreplaylistEntity> nombres;
    private List<GenerosEntity> generos;
    private List<EmpleadosEntity> empleado;
    private List<PlaylistEntity> playlista;

    public PlaylistBean() {
        playlist = new PlaylistEntity();
        genero = new GenerosEntity();
        empleados = new EmpleadosEntity();
        song = new MusicEntity();
        nombre = new NombreplaylistEntity();
    }

    public PlaylistEntity getPlayLista() {
        return playlist;
    }

    public void setPlayLista(PlaylistEntity playlist) {
        this.playlist = playlist;
    }

    public List<PlaylistEntity> getPlaylista() {
        /* Notese que se llama al método listarEstudiantes
 para obtener la lista de objetos a partir de la bd */
        return modelo.listarPlayList();
    }

    public void guardar2(){
        String id = JsfUtil.getRequest().getParameter("idMusic");
        String Usuario = JsfUtil.getRequest().getParameter("codigo");
        String Mail = JsfUtil.getRequest().getParameter("correo");
        // date
        System.out.println(id);
        System.out.println(Usuario);
        System.out.println(Mail);

    }
            public String guardarPlayLista(int idPlayList) {
            int dato1 = 123459;
            empleados.setCodigoEmpleado(dato1);
            String dato = "Activo";
            playlist.setEstado(dato);
            playlist.setIdMusic(song);
            playlist.setIdNombrePlayList(nombre);
            playlist.setIdUser(empleados);
        if (modelo.obtenerPlayList1(idPlayList) == 1) {

            if (modelo.modificarPlayList(playlist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        } else {

            if (modelo.insertarPlayList(playlist) != 1) {
                // JsfUtil.setErrorMessage(null, "Ya se registró un alumno con este carnet");
                return null;//Regreso a la misma página
            } else {
                JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
                //Forzando la redirección en el cliente
                return null;
            }
        }
    }

    public String eliminarPlayLista() {
        // Leyendo el parametro enviado desde la vista
        //Cambiar carnet por ID
        String idPlayList = JsfUtil.getRequest().getParameter("idPlayList");

        if (modelo.eliminarEmpleados(Integer.parseInt(idPlayList)) > 0) {
            JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroEstudiantes?faces-redirect=true";
    }

    public void obtenerPlayLista() {
        String idPlayList = JsfUtil.getRequest().getParameter("idPlayList");
        playlist = modelo.obtenerPlayList(Integer.parseInt(idPlayList));

        // JsfUtil.setFlashMessage("exito", "Estudiante eliminado exitosamente");
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
     * @return the modelo3
     */
    public GenerosModel getModelo3() {
        return modelo3;
    }

    /**
     * @param modelo3 the modelo3 to set
     */
    public void setModelo3(GenerosModel modelo3) {
        this.modelo3 = modelo3;
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
     * @return the song
     */
    public MusicEntity getSong() {
        return song;
    }

    /**
     * @param song the song to set
     */
    public void setSong(MusicEntity song) {
        this.song = song;
    }

    /**
     * @return the songs
     */
    public List<MusicEntity> getSongs() {
        return songs;
    }

    /**
     * @param songs the songs to set
     */
    public void setSongs(List<MusicEntity> songs) {
        this.songs = songs;
    }

    /**
     * @return the nombre
     */
    public NombreplaylistEntity getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(NombreplaylistEntity nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the nombres
     */
    public List<NombreplaylistEntity> getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(List<NombreplaylistEntity> nombres) {
        this.nombres = nombres;
    }
}
