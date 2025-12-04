package projetomadeira.poo.dao;

import java.util.List;
import jakarta.persistence.EntityManager;

public abstract class GenericDAO<T> { // aceita todas as classes

    private final Class<T> entityClass;
    protected EntityManager em;

    public GenericDAO(Class<T> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    public void salvar(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public T buscarPorId(Long id) {
        return em.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> listarTodos() {
        String jpql = "FROM " + entityClass.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    public void excluir(Long id) {
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
