package projetomadeira.poo.dao;

import projetomadeira.poo.entidade.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {

    public ProdutoDAO() {
        super(Produto.class); // cuida de Produto
    }

}
