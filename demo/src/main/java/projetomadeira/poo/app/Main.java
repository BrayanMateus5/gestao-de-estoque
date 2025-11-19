package projetomadeira.poo.app;

import projetomadeira.poo.dao.ClienteDAO;
import projetomadeira.poo.dao.FornecedorDAO;
import projetomadeira.poo.dao.ProdutoDAO;
import projetomadeira.poo.entidade.Cliente;
import projetomadeira.poo.entidade.Fornecedor;
import projetomadeira.poo.entidade.Produto;
import projetomadeira.poo.service.ServicoDeEstoque;
import projetomadeira.poo.util.JPAUtil;

public class Main {
    public static void main(String[] args) {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ServicoDeEstoque servicoDeEstoque = new ServicoDeEstoque();

        System.out.println("Vamo começar a gestão de estoque!");

        Produto produtoTeste = new Produto("Burguesa", "Cerveja Pilsen", 2.50, 3.99, 24.0, 12);
        Cliente clienteTeste = new Cliente("Marcelo", "MarceloEmail@gmail.com", "095.784.478-88");
        Fornecedor fornecedorTeste = new Fornecedor("Casa de Conti", "contato@casadeconti.com", "07.526.557/0005-33");

        produtoDAO.salvar(produtoTeste);
        clienteDAO.salvar(clienteTeste);
        fornecedorDAO.salvar(fornecedorTeste);

        System.out.println("Dados cadastrados com sucesso!");
        System.out.println("Produto no ID: " + produtoTeste.getId());

        System.out.println("/nTeste de entrada de estoque/n");

        servicoDeEstoque.registrarEntrada(produtoTeste.getId(), 10);

        Produto produtoAtualizado = produtoDAO.buscarPorId(produtoTeste.getId());
        System.out.println("Quantidade atualizada do produto " + produtoAtualizado.getNome() + ": "
                + produtoAtualizado.getQuantidadeEstoque());
        JPAUtil.close();

    }
}