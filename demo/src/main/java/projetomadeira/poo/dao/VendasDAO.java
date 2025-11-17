package projetomadeira.poo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.Vendas;
import projetomadeira.poo.util.JPAUtil;

public class VendasDAO {

    // Pra salvar a venda

    public void salvar(Vendas venda) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar a venda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Vendas buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Vendas.class, id);
        } finally {
            em.close();
        }
    }

    public List<Vendas> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vendas v", Vendas.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void apagar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Vendas venda = em.find(Vendas.class, id);
            if (venda != null) {
                em.remove(venda);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao apagar a venda: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
