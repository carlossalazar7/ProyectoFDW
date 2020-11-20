/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.model;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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

    public EmpleadosEntity recuperarContrasenas(EmpleadosEntity us) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(20);
        Random passw = new Random();
        for (int i = 0; i < 10; i++) {
            char c = chars[passw.nextInt(chars.length)];
            sb.append(c);
        }
        String passwordEmpresa = sb.toString();
        EmpleadosEntity usuario = null;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {

            String nombre = us.getUsuarioEmpleado();
            String email = us.getCorreo();
            System.out.println("Antes consulta");
            System.out.println(nombre);
            System.out.println(passwordEmpresa);
            System.out.println(email);
            EmpleadosEntity emp = new EmpleadosEntity();
            et.begin();
            consulta = "UPDATE EmpleadosEntity SET contrasena = :contra WHERE usuarioEmpleado = :user AND correo = :email";
            Query query = em.createQuery(consulta);
            query.setParameter("contra", passwordEmpresa);
            query.setParameter("user", nombre);
            query.setParameter("email", email);
            emp.setContrasena(passwordEmpresa);
            System.out.println("Despues consulta");
            System.out.println(nombre);
            System.out.println(passwordEmpresa);
            System.out.println(email);
            query.executeUpdate();
            et.commit();
            // El correo gmail de envío
            String correoEnvia = "desafiodwf2020@gmail.com";
            String claveCorreo = "DESAFIO2020";

            // La configuración para enviar correo
            Properties properties = new Properties();

            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.user", correoEnvia);
            properties.put("mail.password", claveCorreo);

            Session session = Session.getInstance(properties, null);
            int aviso = 0;
            try {
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(correoEnvia, "Empresa"));
                InternetAddress[] internetAddresses = {new InternetAddress(email)};
                mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
                mimeMessage.setSubject("Contraseña");
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setText("Estimad@ Usuario:" + nombre);
                MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
                mimeBodyPart2.setText("\nSu nueva contraseña es: " + passwordEmpresa);
                // Creo la parte del mensaje
                MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
                mimeBodyPartAdjunto.attachFile("C:/music.jpg");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                multipart.addBodyPart(mimeBodyPart2);
                multipart.addBodyPart(mimeBodyPartAdjunto);
                mimeMessage.setContent(multipart);
                javax.mail.Transport transport = session.getTransport("smtp");
                transport.connect(correoEnvia, claveCorreo);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

                transport.close();

            } catch (IOException | MessagingException ex) {
                aviso = 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return usuario;
    }

    public EmpleadosEntity modificarContrasenas(EmpleadosEntity us) {
        EmpleadosEntity usuario = null;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            String nombre = us.getUsuarioEmpleado();
            String pass = us.getContrasena();
            String email = us.getCorreo();
            System.out.println("Antes consulta");
            System.out.println(nombre);
            System.out.println(pass);
            System.out.println(email);
            EmpleadosEntity emp = new EmpleadosEntity();
            et.begin();
            consulta = "UPDATE EmpleadosEntity SET contrasena = :contra WHERE usuarioEmpleado = :user AND correo = :email";
            Query query = em.createQuery(consulta);
            query.setParameter("contra", pass);
            query.setParameter("user", nombre);
            query.setParameter("email", email);
            emp.setContrasena(pass);
            System.out.println("Despues consulta");
            System.out.println(nombre);
            System.out.println(pass);
            System.out.println(email);
            query.executeUpdate();
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        return usuario;
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return usuario;
    }

    public EmpleadosEntity obtenerUser(String code) {
        EmpleadosEntity usuario = null;
        String consulta;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            consulta = "SELECT e FROM EmpleadosEntity e WHERE e.usuarioEmpleado = :nombre  ";
            Query query = em.createQuery(consulta);
            query.setParameter("nombre", code);
            List<EmpleadosEntity> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
        System.out.println(usuario.getNombreEmpleado());
        return usuario;
    }
}
