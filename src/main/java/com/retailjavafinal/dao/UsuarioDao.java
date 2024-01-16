package com.retailjavafinal.dao;

import com.retailjavafinal.models.Usuario;
import com.retailjavafinal.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UsuarioDao {

    public Usuario findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<Usuario> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario").list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Usuario findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery("FROM Usuario WHERE userName = :username", Usuario.class);
            query.setParameter("username", username);
            Usuario userEncontrado = new Usuario();
            for (Usuario userList:query.list()) {
                userEncontrado.setId(userList.getId());
                userEncontrado.setNombre(userList.getNombre());
                userEncontrado.setUserName(userList.getUserName());
                userEncontrado.setContrasenia(userList.getContrasenia());
                userEncontrado.setApellido(userList.getApellido());
                userEncontrado.setDireccion(userList.getDireccion());
                userEncontrado.setCorreoElectronico(userList.getCorreoElectronico());
                userEncontrado.setTelefono(userList.getTelefono());
                userEncontrado.setTipo_usuario(userList.getTipo_usuario());
            }
            return userEncontrado;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insert(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void update(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(usuario);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void delete(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
