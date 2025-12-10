package projetomadeira.poo.app;

import javax.swing.JOptionPane;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import projetomadeira.poo.entidade.Produto;
import projetomadeira.poo.service.ClienteService;
import projetomadeira.poo.service.FornecedorService;
import projetomadeira.poo.service.ProdutoService;
import projetomadeira.poo.service.ServicoDeEstoque;
import projetomadeira.poo.service.ServicoDeVenda;

public class SistemaGEP {
    private EntityManagerFactory emf;
    private EntityManager em;

    private ProdutoService produtoService;
    private ClienteService clienteService;
    private FornecedorService fornecedorService;
    private ServicoDeEstoque servicoDeEstoque;
    private ServicoDeVenda servicoDeVenda;

    public SistemaGEP() {
        this.emf = Persistence.createEntityManagerFactory("persistenciaPU");
        this.em = emf.createEntityManager();

        this.produtoService = new ProdutoService(em);
        this.clienteService = new ClienteService(em);
        this.fornecedorService = new FornecedorService(em);
        this.servicoDeEstoque = new ServicoDeEstoque(em);
        this.servicoDeVenda = new ServicoDeVenda(em);
    }

    public void iniciar() {
        boolean rodando = true;
        while (rodando) {
            String menu = "====Sistema GEP==== \n" +
                    "1. Cadastrar Fornecedor\n" + "2. Cadastrar cliente\n"
                    + "3. Cadastrar Produto\n" + "4. Entrada de Estoque\n"
                    + "5. Realizar Venda\n" + "6. Consultar Produto\n"
                    + "0. Sair";

            String opcao = JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.QUESTION_MESSAGE);

            if (opcao == null)
                break;
            switch (opcao) {
                case "1":
                    cadastrarFornecedor();
                    break;
                case "2":
                    cadastrarCliente();
                    break;
                case "3":
                    cadastrarProduto();
                    break;
                case "4":
                    entradaEstoque();
                    break;
                case "5":
                    realizarVenda();
                    break;
                case "6":
                    consultarProduto();
                    break;
                case "0":
                    rodando = false;
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema..");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "opção inválida");
            }
        }
        fechar();
    }

    private void cadastrarFornecedor() {
        String nome = JOptionPane.showInputDialog("Nome do Fornecedor: ");
        if (nome == null)
            return;

        String email = JOptionPane.showInputDialog("Email: ");
        String cnpj = JOptionPane.showInputDialog("CNPJ: ");
        try {
            fornecedorService.cadastrar(nome, email, cnpj);
            JOptionPane.showMessageDialog(null, "Fornecedor salvo !!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao salvar: " + e.getMessage());
        }
    }

    private void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Nome do Cliente: ");
        if (nome == null)
            return;

        String email = JOptionPane.showInputDialog("Email: ");
        String cpf = JOptionPane.showInputDialog("CPF: ");
        try {
            clienteService.cadastrar(nome, email, cpf);
            JOptionPane.showMessageDialog(null, "Cliente salvo !!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao tentar salvar: " + e.getMessage());
        }
    }

    private void cadastrarProduto() {
        String nome = JOptionPane.showInputDialog("Nome do Produto");
        if (nome == null)
            return;

        String desc = JOptionPane.showInputDialog("Descrição: ");
        double custo = lerNumero("Preço de Custo");
        if (custo == -1)
            return;
        double venda = lerNumero("Preço de Venda");
        if (venda == -1)
            return;
        double estoque = lerNumero("Estoque inicial: ");
        if (estoque == -1)
            return;
        int min = (int) lerNumero("Estoque Mínimo");
        if (min == -1)
            return;

        try {
            produtoService.cadastrar(nome, desc, custo, venda, estoque, min);
            JOptionPane.showMessageDialog(null, "Produto salvo com Sucesso !!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO ao salvar: " + e.getMessage());
        }
    }

    private void entradaEstoque() {
        long id = (long) lerNumero("ID do produto: ");
        if (id == -1)
            return;

        int qtd = (int) lerNumero("Quantidade a ser adicionada : ");
        if (qtd == -1)
            return;
        try {
            servicoDeEstoque.registrarEntrada(id, qtd);
            JOptionPane.showMessageDialog(null, "Estoque atualizado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no estoque: " + e.getMessage());
        }
    }

    private void realizarVenda() {
        long idCli = (long) lerNumero("ID do Cliente:");
        if (idCli == -1)
            return;
        long idProd = (long) lerNumero("ID do Produto:");
        if (idProd == -1)
            return;
        int qtd = (int) lerNumero("Quantidade vendida:");
        if (qtd == -1)
            return;
        try {
            servicoDeVenda.registrarVenda(idCli, idProd, qtd);
            JOptionPane.showMessageDialog(null, "Venda realizada!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na venda: " + e.getMessage());
        }
    }

    private void consultarProduto() {
        long id = (long) lerNumero("ID do Produto para busca:");
        if (id == -1)
            return;

        try {
            Produto p = produtoService.buscarPorId(id);
            if (p != null) {
                JOptionPane.showMessageDialog(null,
                        "Produto: " + p.getNome() + "\n" +
                                "Preço: R$ " + p.getPrecoVenda() + "\n" +
                                "Estoque: " + p.getQuantidadeEstoque());
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na busca: " + e.getMessage());
        }
    }

    private void fechar() {
        if (em != null && em.isOpen())
            em.close();
        if (emf != null && emf.isOpen())
            emf.close();
    }
    private double lerNumero(String msg) {
        String s = JOptionPane.showInputDialog(msg);
        if (s == null)
            return -1;

        try {
            return Double.parseDouble(s.replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite apenas números!");
            return -1;
        }
    }
}
