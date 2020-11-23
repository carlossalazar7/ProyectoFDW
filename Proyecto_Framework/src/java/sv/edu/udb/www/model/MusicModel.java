/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.www.entities.EmpleadosEntity;
import sv.edu.udb.www.entities.GenerosEntity;
import sv.edu.udb.www.entities.PlaylistEntity;
import sv.edu.udb.www.entities.MusicEntity;
import sv.edu.udb.www.entities.VentasEntity;
import sv.edu.udb.www.utils.DBConnection;
import sv.edu.udb.www.utils.JpaUtil;

/**
 *
 * @author Lenovo
 */
public class MusicModel {

    private MusicEntity music;
    private GenerosEntity genero;
    protected PreparedStatement st;
    protected CallableStatement cs;
    protected ResultSet rs;

    public MusicModel() {
        music = new MusicEntity();
        genero = new GenerosEntity();
    }

    public List<MusicEntity> listarCanciones() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("MusicEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<MusicEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }//Final de listar

    public List<MusicEntity> topCanciones() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("MusicEntity.findTop");
            consulta.setFirstResult(0);
            consulta.setMaxResults(10);

            List<MusicEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;

        } catch (Exception e) {
            em.close();
            return null;

        }
    }

    public MusicEntity obtenerCancion(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            MusicEntity cancion = em.find(MusicEntity.class, id);
            em.close();
            return cancion;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public MusicEntity obtenerMusica(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            MusicEntity cancion = em.find(MusicEntity.class, id);
            em.close();
            return cancion;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int obtenerCancion1(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            MusicEntity cancion = em.find(MusicEntity.class,
                    id);
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int insertarCancion(MusicEntity cancion) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(cancion); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarCanciones(MusicEntity cancion) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(cancion); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarCanciones(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            MusicEntity est = em.find(MusicEntity.class, id);
            if (est != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(est);//Borrando la instancia
                tran.commit();//Confirmando la transacción
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int obtenerLike(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            MusicEntity cancion = em.find(MusicEntity.class, id);
            int likes = cancion.getLikes();
            int operacion = likes + 1;
            System.out.println("Likes obtenidos");
            System.out.println(likes);
            System.out.println(operacion);
            em.close();
            return operacion;
        } catch (Exception e) {
            System.out.println("ERRRRORRRR");
            em.close();
            return 0;
        }
    }

    public void darLike(int id, int operacion) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            MusicEntity cancion = em.find(MusicEntity.class, id);
            tran.begin();
            cancion.setLikes(operacion);
            em.merge(cancion);
            tran.commit();
            System.out.println("LIKEADO");
            em.close();

        } catch (Exception e) {
            System.out.println("NO LIKEADO");
            em.close();

        }

    }

    public List<MusicEntity> ListadoMusica(int id) {
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println(id);
        try {
            consulta = "SELECT m, g.id FROM MusicEntity m INNER JOIN GenerosEntity g ON m.id.id = g.id WHERE g.id = :id";
            Query query = em.createQuery(consulta);
            query.setParameter("id", id);
            List<MusicEntity> lista = query.getResultList();
            System.out.println("llego al try  correctamente");
            return lista;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public MusicEntity modificarContrasenas(MusicEntity us, EmpleadosEntity empl) {
        MusicEntity usuario = null;
        EmpleadosEntity usuario2 = null;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            int idMusic = us.getIdMusic();
            String Usuario = empl.getNombreEmpleado();
            String email = empl.getCorreo();
            System.out.println("Antes consulta");
            System.out.println(idMusic);
            System.out.println(Usuario);
            System.out.println(email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return usuario;
    }

    public List<VentasEntity> listar(String code) {
        VentasEntity usuario = null;
        MusicEntity music = null;
        List<VentasEntity> lista;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            EmpleadosEntity emp = new EmpleadosEntity();
            consulta = "SELECT v FROM VentasEntity v INNER JOIN EmpleadosEntity e  WHERE e.usuarioEmpleado = :codigo";
            Query query = em.createQuery(consulta);
            query.setParameter("codigo", code);
            System.out.println("Este es el id");
            System.out.println(code);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<MusicEntity> ListadoMusicaGenero(int id) {
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        genero.setId(id);
        consulta = "SELECT m  FROM MusicEntity m WHERE m.id = :id";
        Query query = em.createQuery(consulta);
        query.setParameter("id", genero);
        List<MusicEntity> lista = query.getResultList();
        System.out.println("llego al try  correctamente y el id es: " + id);
        return lista;
    }

    public List<PlaylistEntity> listar2(String code) {
        PlaylistEntity usuario = null;
        MusicEntity music = null;
        List<PlaylistEntity> lista;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            EmpleadosEntity emp = new EmpleadosEntity();
            consulta = "SELECT v FROM PlaylistEntity v INNER JOIN EmpleadosEntity e WHERE e.usuarioEmpleado = :codigo";
            Query query = em.createQuery(consulta);
            query.setParameter("codigo", code);
            System.out.println("Este es el id");
            System.out.println(code);
            lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * @return the music
     */
    public MusicEntity getMusic() {
        return music;
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
}
