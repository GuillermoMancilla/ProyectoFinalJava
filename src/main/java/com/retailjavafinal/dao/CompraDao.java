package com.retailjavafinal.dao;

import com.retailjavafinal.models.Compra;
import com.retailjavafinal.models.DetalleCompra;
import com.retailjavafinal.models.Producto;
import com.retailjavafinal.models.Usuario;
import com.retailjavafinal.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public List<Compra> findAllofUserCpas(long idUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Compra> query = session.createQuery("FROM Compra WHERE usuario_id = :usuario_id", Compra.class);
            query.setParameter("usuario_id", idUser);
            return query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addDetalleCompra(Long compraId, DetalleCompra detalleCompra, Long productoId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Obtener el autor
            Compra compra = session.get(Compra.class, compraId);

            //Obtenemos el genero
            Producto producto = session.get(Producto.class,productoId);

            if (compra != null && producto != null){
                // Asignar el libro al autor
                compra.agregarCompra(detalleCompra);

                //asignamos el libro al genero
                producto.agregarCompra(detalleCompra);

                // Guardar la actualización
                session.saveOrUpdate(compra);
                session.saveOrUpdate(producto);
            }

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
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
