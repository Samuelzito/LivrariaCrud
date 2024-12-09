package edu.Livraria.model.services;

import edu.Livraria.model.entity.Livro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class LivroServices {

    private EntityManager em;

    // Construtor com EntityManager
    public LivroServices(EntityManager em) {
        this.em = em;
    }

    // Construtor padrão (caso queira usar sem passar um EntityManager)
    public LivroServices() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
            this.em = emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao inicializar EntityManager: " + e.getMessage());
        }
    }

    public void salvar(Livro livro) {
        try {
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void atualizar(Livro livro) {
        try {
            em.getTransaction().begin();
            em.merge(livro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        try {
            em.getTransaction().begin();
            Livro livro = em.find(Livro.class, id);
            if (livro != null) {
                em.remove(livro);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Livro> listarTodos() {
        try {
            return em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void fechar() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
