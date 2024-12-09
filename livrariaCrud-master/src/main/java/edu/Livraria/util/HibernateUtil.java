package edu.Livraria.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Carregar o arquivo hibernate.properties
            Properties properties = new Properties();
            properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));

            // Configurar o Hibernate com as propriedades
            Configuration configuration = new Configuration();
            configuration.addProperties(properties);

            // Adicionar a entidade Livro
            configuration.addAnnotatedClass(edu.Livraria.model.entity.Livro.class);

            // Registrar o ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // Construir o SessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (IOException ex) {
            System.err.println("Erro ao carregar hibernate.properties: " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        } catch (Throwable ex) {
            System.err.println("Falha ao inicializar o Hibernate SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
