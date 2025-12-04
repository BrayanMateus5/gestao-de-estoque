package projetomadeira.poo.service;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.dao.ClienteDAO;
import projetomadeira.poo.entidade.Cliente;

public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService(EntityManager em) {
        this.clienteDAO = new ClienteDAO(em);

    }

    // Parte de cadastro

    public Cliente cadastrar(String nome, String email, String cpf) {
        if (cpf == null || cpf.length() < 11) {
            System.out.println("Erro: CPF inválido. Não foi possível cadastrar");
            return null;
        }
        if (nome == null || nome.isEmpty()) {
            System.out.println("ERRO. O nome é obrigatório");
            return null;
        }

        Cliente novoCliente = new Cliente(nome, email, cpf);
        clienteDAO.salvar(novoCliente);

        System.out.println("Cliente cadastrado com sucesso: " + novoCliente.getNome());
        return novoCliente;
    }
}
