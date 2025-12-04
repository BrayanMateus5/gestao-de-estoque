package projetomadeira.poo.dao;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.MovimentacaoEstoque;

public class MovimentacaoEstoqueDAO extends GenericDAO<MovimentacaoEstoque> {

    public MovimentacaoEstoqueDAO(EntityManager em) {
        super(MovimentacaoEstoque.class, em);
    }
}
