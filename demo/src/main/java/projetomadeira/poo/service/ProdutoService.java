package projetomadeira.poo.service;

import projetomadeira.poo.dao.ProdutoDAO;
import projetomadeira.poo.entidade.Produto;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public Produto cadastrar(String nome, String descricao, double custo, double venda, double estoque,
            Integer minimo) {

        if (custo < 0 || venda < 0) {
            System.out.println("ERRO : Os preços não podem ser negativos");
            return null;
        }

        if (venda < custo) {
            System.out.println("Aviso : O preço para venda está menor que o de custo");
        }
        Produto produto = new Produto(nome, descricao, custo, venda, estoque, minimo);
        produtoDAO.salvar(produto);

        System.out.println("Produto cadastrado: " + produto.getNome());
        return produto;
    }

    public void atualizarEstoque(Long idProduto, Double novaQuantidade) {
        Produto produto = produtoDAO.buscarPorId(idProduto);

        if (produto != null) {
            produto.setQuantidadeEstoque(novaQuantidade);
            produtoDAO.salvar(produto);
            System.out.println("Estoque em produto " + produto.getNome() + "atualizado para : " + novaQuantidade);
        } else {
            System.out.println("ERRO: Produto não encontrado para atualização.");

        }
    }

    public Produto buscarPorId(Long id) {
        return produtoDAO.buscarPorId(id);
    }
}
