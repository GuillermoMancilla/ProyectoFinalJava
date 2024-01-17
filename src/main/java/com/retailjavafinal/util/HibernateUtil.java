package com.retailjavafinal.util;

import com.retailjavafinal.models.Compra;
import com.retailjavafinal.models.DetalleCompra;
import com.retailjavafinal.models.Producto;
import com.retailjavafinal.models.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Propiedades de conexio de Hibernate
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/proyectofinalretail");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");

                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //Manejar la construccion de tablas de la base de datos
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                //Asociar las clases
                configuration.addAnnotatedClass(Compra.class);// Añade esta línea
                configuration.addAnnotatedClass(DetalleCompra.class);// Añade esta línea
                configuration.addAnnotatedClass(Producto.class);// Añade esta línea
                configuration.addAnnotatedClass(Usuario.class);// Añade esta línea
                //Servicio de parametros de conexion
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                //Crear conexion. Será utilizada por los DAO's
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
