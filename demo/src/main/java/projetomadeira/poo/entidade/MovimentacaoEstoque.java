package projetomadeira.poo.entidade;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimentacoes_estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataMovimentacao;
    private int quantidade;

    @Enumerated(EnumType.STRING) // Salva o texto de Entrada e saída
    private TipoMovimentacao tipo;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public MovimentacaoEstoque() {
    }
    public MovimentacaoEstoque(int quantidade, TipoMovimentacao tipo, Produto produto) {
        this.dataMovimentacao = LocalDateTime.now();
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.produto = produto;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public TipoMovimentacao getTipo() {
        return tipo;
    }
    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    

}
