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
import sv.edu.udb.www.entities.PaquetesEntity;
import sv.edu.udb.www.utils.*;
/**
 *
 * @author Lenovo
 */
public class PaquetesModel {
    public List<PaquetesEntity> listarPaquetes() {
        //Obtengo una instancia de EntityManager
        EntityManager paque = JpaUtil.getEntityManager();
        try {
            Query consulta = paque.createNamedQuery("PaquetesEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<PaquetesEntity> lista = consulta.getResultList();
            paque.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            paque.close();
            return null;
        }
    }//Final de listar

    public PaquetesEntity obtenerPaquetes(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            System.out.println(id);
            PaquetesEntity paquete = em.find(PaquetesEntity.class, id);
            em.close();
            return paquete;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int obtenerPaquetes1(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            PaquetesEntity paquete = em.find(PaquetesEntity.class,
                    id);
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int insertarPaquete(PaquetesEntity paquete) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(paquete); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarEmpleados(PaquetesEntity paquete) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(paquete); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarEmpleados(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            PaquetesEntity est = em.find(PaquetesEntity.class, id);
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
