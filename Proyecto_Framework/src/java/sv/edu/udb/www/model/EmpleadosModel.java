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
import sv.edu.udb.www.utils.*;

/**
 *
 * @author Lenovo
 */
public class EmpleadosModel {

    public List<EmpleadosEntity> listarEmpleados() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("EmpleadosEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<EmpleadosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }//Final de listar

    public EmpleadosEntity obtenerEmpleados(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            EmpleadosEntity empleado = em.find(EmpleadosEntity.class, codigo);
            em.close();
            return empleado;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int obtenerEmpleados1(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            EmpleadosEntity empleado = em.find(EmpleadosEntity.class,
                    codigo);
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int insertarEmpleados(EmpleadosEntity empleado) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(empleado); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int modificarEmpleados(EmpleadosEntity empleado) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(empleado); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarEmpleado(int codigo) {
        EntityManager em = JpaUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            EmpleadosEntity est = em.find(EmpleadosEntity.class, codigo);
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

    public EmpleadosEntity iniciarSesion(EmpleadosEntity us) {
        EmpleadosEntity usuario = null;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String nombre = us.getUsuarioEmpleado();
            String pass = us.getContrasena();
            EmpleadosEntity emp = new EmpleadosEntity();
            emp.setNombreEmpleado(nombre);
            emp.setContrasena(pass);
            
            consulta = "SELECT e FROM EmpleadosEntity e WHERE e.usuarioEmpleado = :nombre and e.contrasena = :pass ";
            Query query = em.createQuery(consulta);
            query.setParameter("nombre", nombre);
            query.setParameter("pass", pass);
            List<EmpleadosEntity> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
          /*  if (lista != null) {
                System.out.println("Datos encontrados");
            } else {
                System.out.println("Datos no encontrados");
            }*/
        } catch (Exception e) {
            System.out.println(e);
        }
        return usuario;
    }
}
