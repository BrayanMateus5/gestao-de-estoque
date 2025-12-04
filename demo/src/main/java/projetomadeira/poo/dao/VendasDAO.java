package projetomadeira.poo.dao;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.Vendas;

public class VendasDAO extends GenericDAO<Vendas> {

    public VendasDAO(EntityManager em) {
        super(Vendas.class, em); // cuida de classe
    }
}
