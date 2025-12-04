package projetomadeira.poo.dao;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.entidade.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {

    public ClienteDAO(EntityManager em) {
        super(Cliente.class, em); // Aqui cuida de cliente
    }
}
