package projetomadeira.poo.service;

import jakarta.persistence.EntityManager;
import projetomadeira.poo.dao.ClienteDAO;
import projetomadeira.poo.dao.ProdutoDAO;
import projetomadeira.poo.dao.VendasDAO;
import projetomadeira.poo.entidade.Cliente;
import projetomadeira.poo.entidade.ItemVenda;
import projetomadeira.poo.entidade.Produto;
import projetomadeira.poo.entidade.Vendas;

public class ServicoDeVenda {

    private VendasDAO vendasDAO;
    private ProdutoDAO produtoDAO;
    private ClienteDAO clienteDAO;

    public ServicoDeVenda(EntityManager em) {
        this.vendasDAO = new VendasDAO(em);
        this.produtoDAO = new ProdutoDAO(em);
        this.clienteDAO = new ClienteDAO(em);
    }

    public void registrarVenda(Long clienteId, Long produtoId, Integer quantidade) {
        // Verifica se o cliente existe
        Cliente cliente = clienteDAO.buscarPorId(clienteId);
        if (cliente == null) {
            System.out.println("Erro: Cliente não encontrado.");
            return;
        }
        Vendas venda = new Vendas(cliente);

        Produto produto = produtoDAO.buscarPorId(produtoId);
        // vai validar a existencia do produto
        if (produto == null) {
            System.out.println("Erro: Produto não encontrado.");
            return;
        }

        // valida a quantidade em estoque
        Double quantidadeEstoque = produto.getQuantidadeEstoque() != null ? produto.getQuantidadeEstoque() : 0.0;
        if (quantidadeEstoque < quantidade) {
            System.out.println("Erro: Estoque insuficiente para o produto " + produto.getNome()
                    + ". O que tem disponível: " + produto.getQuantidadeEstoque());
            return;
        }
        // cria o item da venda
        ItemVenda itemVenda = new ItemVenda(quantidade, produto.getPrecoVenda(), venda, produto);

        // Bota na lista
        venda.getItens().add(itemVenda);

        // Atualiza o estoque do produto
        produto.setQuantidadeEstoque(quantidadeEstoque - quantidade);
        produtoDAO.salvar(produto);

        // puxa o valor total
        double valorTotal = produto.getPrecoVenda() * quantidade;
        venda.setValor(valorTotal);
        vendasDAO.salvar(venda);
        System.out.println("A venda foi feita meu patrão. Custou total de R$ " + valorTotal);
    }
}
