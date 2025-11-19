package projetomadeira.poo.service;

import java.util.Map;

import org.hibernate.annotations.ParamDef;

import projetomadeira.poo.dao.ClienteDAO;
import projetomadeira.poo.dao.ProdutoDAO;
import projetomadeira.poo.dao.VendasDAO;
import projetomadeira.poo.entidade.Produto;
import projetomadeira.poo.entidade.Vendas;

public class ServicoDeVenda {

    private VendasDAO vendasDAO;
    private ProdutoDAO produtoDAO;
    private ClienteDAO clienteDAO;

    public ServicoDeVenda() {
        this.vendasDAO = new VendasDAO();
        this.produtoDAO = new ProdutoDAO();
        this.clienteDAO = new ClienteDAO();
    }

    @param
    @Param

    public void registrarVenda(Long produtoId, Long clienteId, Map<Long, Integer> produtosVendidos) {
        // Verifica se o cliente existe
        var cliente = clienteDAO.buscarPorId(clienteId);
        if (cliente == null) {
            System.out.println("Erro: Cliente não encontrado.");
            return;
        }
        //Começa cas venda
        Vendas venda = new Vendas();
        double totalVenda = 0.0;

        // tem no estoque ?
        for (Map.Entry<Long, Integer> Entry : produtosVendidos.entrySet()) {
            Long id = Entry.getKey();
            Integer quantidade = Entry.getValue();

            //existe ?
            Produto produto = produtoDAO.buscarPorId(id);
            if (produto == null) {
                System.out.println("Erro: Produto com ID " + id + " não encontrado.");
                return;
            }
            produto.setQuantidadeEstoque(estoqueAtual - quantidade);
                       if (produto.getQuantidadeEstoque() < quantidade) {
                System.out.println("Erro: Estoque insuficiente para o produto " + produto.getNome());
                return;
            }
        }

        // Registra a venda
        vendasDAO.salvarVenda(cliente, produtosVendidos);

        // Atualiza o estoque dos produtos vendidos
        for (Map.Entry<Long, Integer> entry : produtosVendidos.entrySet()) {
            Long id = entry.getKey();
            Integer quantidade = entry.getValue();

            var produto = produtoDAO.buscarPorId(id);
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            produtoDAO.atualizar(produto);
        }

        System.out.println("Venda registrada com sucesso para o cliente: " + cliente.getNome());
    }

}
