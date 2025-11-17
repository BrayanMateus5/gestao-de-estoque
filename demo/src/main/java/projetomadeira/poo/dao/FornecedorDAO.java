package projetomadeira.poo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.Fornecedor;
import projetomadeira.poo.util.JPAUtil;

public class FornecedorDAO {

    // Salvar o tal fornecedor
    public void salvar(Fornecedor fornecedor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fornecedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar o fornecedor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Fornecedor buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public List<Fornecedor> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Fornecedor f", Fornecedor.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Fornecedor fornecedor) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(fornecedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar o fornecedor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Fornecedor fornecedor = em.find(Fornecedor.class, id);
            if (fornecedor != null) {
                em.remove(fornecedor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao deletar o fornecedor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}