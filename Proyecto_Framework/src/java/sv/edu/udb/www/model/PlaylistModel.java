/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.entities.PlaylistEntity;
import sv.edu.udb.www.utils.JpaUtil;

/**
 *
 * @author Lenovo
 */
public class PlaylistModel {

    public List<PlaylistEntity> listarPlayList() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("PlaylistEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<PlaylistEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }//Final de listar

    public PlaylistEntity obtenerPlayList(int idPlayList) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            PlaylistEntity PlayLista = em.find(PlaylistEntity.class, idPlayList);
            em.close();
            return PlayLista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int obtenerPlayList1(int idPlayList) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            PlaylistEntity PlayLista = em.find(PlaylistEntity.class,
                    idPlayList);
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int insertarPlayList(PlaylistEntity PlayLista) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(PlayLista); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarPlayList(PlaylistEntity PlayLista) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(PlayLista); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarEmpleados(int idPlayList) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            PlaylistEntity est = em.find(PlaylistEntity.class, idPlayList);
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

    public List<PlaylistEntity> Lista(HttpServletRequest request) {
        String consulta;
        HttpSession session = request.getSession();
        String cod = (String) session.getAttribute("codigoEmpleado");
        EntityManager em = JpaUtil.getEntityManager();
        try {

            consulta = "SELECT p FROM PlaylistEntity p WHERE p.idUser.codigoEmpleado = :id";
            Query query = em.createQuery(consulta);
            query.setParameter("id", cod);
            List<PlaylistEntity> lista = query.getResultList();
            System.out.println("llego al try  correctamente");
            return lista;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
