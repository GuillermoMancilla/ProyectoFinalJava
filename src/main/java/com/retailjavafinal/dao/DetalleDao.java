package com.retailjavafinal.dao;

import com.retailjavafinal.models.DetalleCompra;
import com.retailjavafinal.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DetalleDao {

    public DetalleCompra findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(DetalleCompra.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<DetalleCompra> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM DetalleCompra", DetalleCompra.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insert(DetalleCompra detalleCompra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(detalleCompra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void update(DetalleCompra detalleCompra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(detalleCompra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void delete(DetalleCompra detalleCompra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(detalleCompra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
    
}
