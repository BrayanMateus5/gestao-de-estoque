package projetomadeira.poo.app;

import java.util.HashMap;
import java.util.Map;

import projetomadeira.poo.entidade.Cliente;
import projetomadeira.poo.entidade.Fornecedor;
import projetomadeira.poo.entidade.Produto;
import projetomadeira.poo.service.ClienteService;
import projetomadeira.poo.service.FornecedorService;
import projetomadeira.poo.service.ProdutoService;
import projetomadeira.poo.service.ServicoDeEstoque;
import projetomadeira.poo.service.ServicoDeVenda;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Começando a Gestão de seu estoque---");

        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();
        FornecedorService fornecedorService = new FornecedorService();
        ServicoDeEstoque servicoDeEstoque = new ServicoDeEstoque();
        ServicoDeVenda servicoDeVenda = new ServicoDeVenda();

        // Primeira fase: Adicionar os produtos, clientes e fornecedores

        Fornecedor SantaMaria = fornecedorService.cadastrar("Madereira SantaMaria", "Madereirareira@gmail.com",
                "44.754.213/3435-54");
        Cliente Osvaldo = clienteService.cadastrar("Osvaldo Silveira", "OsvaldoSil@gmail.com", "456.741.568-78");

        Produto VigaMadeira = produtoService.cadastrar("Pino do coquilho", "Viga de madeira de carvalho", 50.0, 80.0,
                250.0, 10);

        // Guardar ID
        Long idProduto = VigaMadeira.getId();
        Long idCliente = Osvaldo.getId();

        // -- Segunda fase: Entrada do estoque

        System.out.println("--- Vamos receber os produtos ---");

        servicoDeEstoque.registrarEntrada(idProduto, 10);

        // terceira fase : Vamo vender !!
        System.out.println("--- Chegou cliente na loja ---");

        // Cliente pegou o carrinho de compras
        Map<Long, Integer> itensVenda = new HashMap<>();
        itensVenda.put(idProduto, 5); // Colocou as 5 vigas

        servicoDeVenda.registrarVenda(idCliente, itensVenda);

        // Vamos aos conferes

        Produto produtoAtualizado = produtoService.buscarPorId(idProduto);

        System.out.println("Produto : " + produtoAtualizado.getNome());
        System.out.println("Estoque Atual: " + produtoAtualizado.getQuantidadeEstoque());
        System.out.println("Entrada registrada:       //alterar 10 unidades");
        System.out.println("Venda feita           -5.0");
        System.out.println("----------------------------------------------------------");
        System.out.println("Estoque final: " + produtoAtualizado.getQuantidadeEstoque());

        if (produtoAtualizado.getQuantidadeEstoque() == 255.0) {
            System.out.println("\nVAMUUUU!!  Deu certo o sistema");
        } else {
            System.out.println("\n AOW !!  Deu algo de errado aqui");
        }
    }
}