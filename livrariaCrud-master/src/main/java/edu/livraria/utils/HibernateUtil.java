package edu.livraria.utils;

import edu.livraria.model.entity.Livro;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import jakarta.persistence.EntityManager;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    private static final String CONFIG_FILE = "hibernate.properties";
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    static {
        initializeSessionFactory();
    }

    private static void initializeSessionFactory() {
        try {
            Properties hibernateProperties = loadProperties();
            registry = buildRegistry(hibernateProperties);
            sessionFactory = buildSessionFactory(registry);
        } catch (Exception e) {
            destroyRegistry();
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (var inputStream = HibernateUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IOException("Unable to find " + CONFIG_FILE);
            }
            properties.load(inputStream);
            return properties;
        }
    }

    private static StandardServiceRegistry buildRegistry(Properties properties) {
        return new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();
    }

    private static SessionFactory buildSessionFactory(StandardServiceRegistry registry) {
        return new MetadataSources(registry)
                .addAnnotatedClass(Livro.class)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory has not been initialized");
        }
        return sessionFactory;
    }

    public static EntityManager getEntityManager() {
        Session session = getSessionFactory().openSession();
        return session.unwrap(EntityManager.class);
    }

    private static void destroyRegistry() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @SneakyThrows
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
        destroyRegistry();
    }

    private HibernateUtil() {
        throw new UnsupportedOperationException("Utility class");
    }
}
