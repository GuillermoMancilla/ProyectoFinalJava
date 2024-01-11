package com.retailjavafinal.dao;

import com.retailjavafinal.models.Producto;
import com.retailjavafinal.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductoDao {
    public void insert(Producto producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(producto);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
