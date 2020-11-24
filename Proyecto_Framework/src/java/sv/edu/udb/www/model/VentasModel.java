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
import sv.edu.udb.www.entities.EmpleadosEntity;
import sv.edu.udb.www.entities.GenerosEntity;
import sv.edu.udb.www.entities.MusicEntity;
import sv.edu.udb.www.entities.NombreplaylistEntity;
import sv.edu.udb.www.entities.PlaylistEntity;
import sv.edu.udb.www.entities.VentasEntity;
import sv.edu.udb.www.utils.JpaUtil;

/**
 *
 * @author Lenovo
 */
public class VentasModel {
        private EmpleadosEntity empleado;
public VentasModel() {
        
        empleado = new EmpleadosEntity();
    }
    public List<VentasEntity> listarVentas() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("VentasEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<VentasEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }//Final de listar

    public VentasEntity obtenerVentas(int idVenta) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            VentasEntity Ventas = em.find(VentasEntity.class, idVenta);
            em.close();
            return Ventas;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int obtenerVentas1(int idVenta) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            VentasEntity Ventas = em.find(VentasEntity.class,idVenta);
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int insertarVentas(VentasEntity Ventas) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(Ventas); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarVentas(VentasEntity Ventas) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(Ventas); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarVentas(int idVenta) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            VentasEntity est = em.find(VentasEntity.class, idVenta);
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
    public List<VentasEntity> historial2(int code) {
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        empleado.setCodigoEmpleado(code);
        consulta = "SELECT p FROM VentasEntity p WHERE p.idUser = :id";
        Query query = em.createQuery(consulta);
        query.setParameter("id", empleado);
        List<VentasEntity> lista = query.getResultList();
        System.out.println("llego al try  correctamente y el id es: " + code);
        return lista;
    }
    
     public List<VentasEntity> historial(int id) {
        List<VentasEntity> lista;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            consulta = "SELECT p FROM VentasEntity p WHERE p.idUser = :id";
            Query query = em.createQuery(consulta);
            query.setParameter("id", id);
            lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
