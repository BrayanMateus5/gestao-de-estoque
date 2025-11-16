package projetomadeira.poo.entidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;
    private LocalDate dataVenda;

    @ManyToOne // Muitas vendas pra 1 só cliente
    @JoinColumn(name = "cliente_id") // Cria cliente_id em "vendas"
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL) // 1 venda pra muitos itens
    private List<ItemVenda> itens = new ArrayList<>();

    public Vendas() {
    }

    public Vendas(Cliente cliente) {
        this.cliente = cliente;
        this.dataVenda = LocalDate.now();
        this.valor = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Vendas [id=" + id + ", valor=" + valor + ", dataVenda=" + dataVenda + ", cliente=" + cliente + "]";
    }
}
