package projetomadeira.poo.visao;

import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import projetomadeira.poo.service.ProdutoService;

public class TelaCadastroProduto extends JFrame {

    private ProdutoService produtoService;

    // Os campos para a tela de cadastro
    private JTextField txtNome = new JTextField();
    private JTextField txtDescricao = new JTextField();
    private JTextField txtPrecoCusto = new JTextField();
    private JTextField txtPrecoVenda = new JTextField();
    private JTextField txtEstoque = new JTextField();
    private JTextField txtEstoqueMinimo = new JTextField();
    private JButton btnSalvar = new JButton("Salvar produto");

    public TelaCadastroProduto(ProdutoService produtoService) {
        this.produtoService = produtoService;
        this.setTitle("Cadastro de Produto do GEP" );
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(7, 2, 10, 10));
    }
}
