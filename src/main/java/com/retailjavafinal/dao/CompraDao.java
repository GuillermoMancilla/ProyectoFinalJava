package com.retailjavafinal.dao;

import com.retailjavafinal.models.Compra;
import com.retailjavafinal.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CompraDao {

    public Compra findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Compra.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Compra> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Compra", Compra.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void insert(Compra compra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(compra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void update(Compra compra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(compra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void delete(Compra compra) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(compra);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
