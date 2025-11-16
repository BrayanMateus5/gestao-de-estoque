package projetomadeira.poo.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;
    private Double precoUnitario;

    @ManyToOne // Vários itens para a venda na hora
    @JoinColumn(name = "venda_id") // Cria venda_id em "itens_venda"
    private Vendas venda;

    @ManyToOne // Vários itens mesmo que de vendas diferentes podem ser para o mesmo produto
    @JoinColumn(name = "produto_id") // Cria produto_id em "itens_venda"
    private Produto produto;

    public ItemVenda() {
    }

    public ItemVenda(int quantidade, Double precoUnitario, Vendas venda, Produto produto) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.venda = venda;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Vendas getVenda() {
        return venda;
    }

    public void setVenda(Vendas venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    @Override
    public String toString() {
        return "ItemVenda [id=" + id + ", quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + "]";
    }
}
