/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.www.entities.NombreplaylistEntity;
import sv.edu.udb.www.utils.JpaUtil;

/**
 *
 * @author Lenovo
 */
public class NombreplaylistModel {
    public List<NombreplaylistEntity> listarPlayList() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("NombreplaylistEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<NombreplaylistEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }//Final de listar

    public NombreplaylistEntity obtenerPlayList(String idNombrePlayList) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            NombreplaylistEntity PlayList = em.find(NombreplaylistEntity.class, idNombrePlayList);
            em.close();
            return PlayList;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarPlayList(NombreplaylistEntity PlayList) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(PlayList); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarPlayList(NombreplaylistEntity PlayList) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(PlayList); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarPlayList(String idNombrePlayList) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            NombreplaylistEntity est = em.find(NombreplaylistEntity.class, idNombrePlayList);
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
}
