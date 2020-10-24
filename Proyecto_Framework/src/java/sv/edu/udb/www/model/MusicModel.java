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
import sv.edu.udb.www.entities.MusicEntity;
import sv.edu.udb.www.utils.JpaUtil;

/**
 *
 * @author Lenovo
 */
public class MusicModel {
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
    
    public List<MusicEntity> topCanciones(){
         EntityManager em = JpaUtil.getEntityManager();
         try{
        Query consulta = em.createNamedQuery("MusicEntity.findTop");
           consulta.setFirstResult(0);
           consulta.setMaxResults(10);
           
            List<MusicEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
    
    
    
    }catch(Exception e){
        em.close();
        return null;
        
    }}

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
    
    
     public int obtenerLike(int id){
    EntityManager em = JpaUtil.getEntityManager();
    try{
    MusicEntity cancion = em.find(MusicEntity.class, id);
    int likes = cancion.getLikes();
    int operacion = likes + 1;
    System.out.println("Likes obtenidos");
    System.out.println(likes);
    System.out.println(operacion);
    em.close();
    return operacion;
    }
    catch(Exception e ){
    System.out.println("ERRRRORRRR");
    em.close();
    return 0;
    }
    }
    
    public void darLike(int id,int operacion){
     EntityManager em = JpaUtil.getEntityManager();
       EntityTransaction tran = em.getTransaction();
       try{
     MusicEntity cancion = em.find(MusicEntity.class, id);
       tran.begin();
       cancion.setLikes(operacion);
       em.merge(cancion);
       tran.commit();
       System.out.println("LIKEADO");
       em.close();
       
       
       }catch(Exception e){
            System.out.println("NO LIKEADO");
       em.close();
      
       }
       
       
     
    }

}
