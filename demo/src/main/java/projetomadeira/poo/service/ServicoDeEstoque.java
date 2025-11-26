package projetomadeira.poo.service;

import projetomadeira.poo.dao.MovimentacaoEstoqueDAO;
import projetomadeira.poo.dao.ProdutoDAO;
import projetomadeira.poo.entidade.MovimentacaoEstoque;
import projetomadeira.poo.entidade.Produto;
import projetomadeira.poo.entidade.TipoMovimentacao;




// Cliente cliente = new Cliente();
//cliente.getNome();

public class ServicoDeEstoque {

    // Chama os DAOS para acesso
    private ProdutoDAO produtoDAO;
    private MovimentacaoEstoqueDAO movimentacaoEstoqueDAO;

    // Iniciando os DAOS
    public ServicoDeEstoque() {
        this.produtoDAO = new ProdutoDAO();
        this.movimentacaoEstoqueDAO = new MovimentacaoEstoqueDAO();
    }
    // Regras de negócio...

    // Entrada de estoque
    public void registrarEntrada(Long produtoId, int quantidadeSoma) {
        // Olha se pode registrar a entrada
        if (quantidadeSoma <= 0) {
            System.out.println("Erro: A quantidade não pode ser negativa.");
            return;
        }
        // Faz a busca do produto
        Produto produto = produtoDAO.buscarPorId(produtoId);
        // Se não existe, dá erro
        if (produto == null) {
            System.out.println("Erro: Produto não encontrado.");
            return;
        }
        // Atualização de quantidade
        Double estoqueAtual = (produto.getQuantidadeEstoque() != null) ? produto.getQuantidadeEstoque() : 0.0;
        Double novaQuantidade = estoqueAtual + quantidadeSoma;
        produto.setQuantidadeEstoque(novaQuantidade);

        // Cria o registro
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(quantidadeSoma,
                TipoMovimentacao.ENTRADA, produto);

        // persistencia na atualização
        produtoDAO.atualizar(produto);
        // Cria um registro e salva a movimentação no histórico
        movimentacaoEstoqueDAO.salvar(movimentacao);

        System.out.println("Entrada registrada com sucesso para o produto: " + produto.getNome());
    }
}
