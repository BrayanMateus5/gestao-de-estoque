package projetomadeira.poo.service;

import projetomadeira.poo.dao.FornecedorDAO;
import projetomadeira.poo.entidade.Fornecedor;

public class FornecedorService {

    private FornecedorDAO fornecedorDAO;

    public FornecedorService() {
        this.fornecedorDAO = new FornecedorDAO();
    }

    public Fornecedor cadastrar(String nome, String email, String cnpj) {
        if (cnpj == null || cnpj.length() < 14) {

            System.out.println("ERRO: CNPJ não pode ser usado");
            return null;
        }
        if (nome == null || nome.isEmpty()) {
            System.out.println("ERRO: Nome do fornecedor é obrigado");
            return null;
        }
        Fornecedor novoFornecedor = new Fornecedor(nome, email, cnpj);
        fornecedorDAO.salvar(novoFornecedor);

        System.out.println("Fornecedor cadastrado : " + novoFornecedor.getNome());
        return novoFornecedor;

    }

}