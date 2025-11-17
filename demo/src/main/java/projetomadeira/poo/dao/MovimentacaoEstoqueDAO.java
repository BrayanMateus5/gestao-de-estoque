package projetomadeira.poo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.MovimentacaoEstoque;
import projetomadeira.poo.util.JPAUtil;

public class MovimentacaoEstoqueDAO {

    // Salva as movimentações

    public void salvar(MovimentacaoEstoque movimentacao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movimentacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar a movimentação de estoque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<MovimentacaoEstoque> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM MovimentacaoEstoque m", MovimentacaoEstoque.class).getResultList();
        } finally {
            em.close();
        }
    }

    public MovimentacaoEstoque buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(MovimentacaoEstoque.class, id);
        } finally {
            em.close();
        }
    }
}
