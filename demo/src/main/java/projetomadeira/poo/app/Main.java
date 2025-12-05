package projetomadeira.poo.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");
        EntityManager em = emf.createEntityManager();

        try {
            ProdutoService produtoService = new ProdutoService(em);
            ClienteService clienteService = new ClienteService(em);
            FornecedorService fornecedorService = new FornecedorService(em);
            ServicoDeEstoque servicoDeEstoque = new ServicoDeEstoque(em);
            ServicoDeVenda servicoDeVenda = new ServicoDeVenda(em);

            // Primeira fase: Adicionar os produtos, clientes e fornecedores

            Fornecedor SantaMaria = fornecedorService.cadastrar("Madereira SantaMaria", "Madereirareira@gmail.com",
                    "44.754.213/3435-54");
            Cliente Osvaldo = clienteService.cadastrar("Osvaldo Silveira", "OsvaldoSil@gmail.com", "456.741.568-78");

            Produto VigaMadeira = produtoService.cadastrar("Pino do coquilho", "Viga de madeira de carvalho", 50.0,
                    80.0,
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
            servicoDeVenda.registrarVenda(idCliente, idProduto, 5);

            // Vamos aos conferes

            Produto produtoFinal = produtoService.buscarPorId(idProduto);

            System.out.println("Produto : " + produtoFinal.getNome());
            System.out.println("----------------------------------------------------------");
            System.out.println("Estoque final: " + produtoFinal.getQuantidadeEstoque());

            if (produtoFinal.getQuantidadeEstoque() == 255.0) {
                System.out.println("\nVAMUUUU!!  Deu certo o sistema");
            } else {
                System.out.println("\n AOW !!  Deu algo de errado aqui");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            if (emf.isOpen()) {
                emf.close();
            }
        }
    }
}
