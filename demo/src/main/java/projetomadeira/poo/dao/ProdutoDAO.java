package projetomadeira.poo.dao;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {

    public ProdutoDAO(EntityManager em) {
        super(Produto.class, em); // cuida de Produto
    }

}
