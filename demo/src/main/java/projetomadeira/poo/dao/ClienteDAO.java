package projetomadeira.poo.dao;

import projetomadeira.poo.entidade.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {

    public ClienteDAO() {
        super(Cliente.class); // Aqui cuida de cliente
    }
}
