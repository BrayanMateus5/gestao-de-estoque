package projetomadeira.poo.dao;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.Fornecedor;

public class FornecedorDAO extends GenericDAO<Fornecedor> {

    public FornecedorDAO(EntityManager em) {
        super(Fornecedor.class, em); // cuida de Fornecedor
    }
}
