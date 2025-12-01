package projetomadeira.poo.entidade;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")

public class Cliente extends Pessoa {

    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Vendas> vendas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nome, String email, String cpf) {
        super(nome, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public List<Vendas> getVendas() {
        return vendas;
    }

    public void setVendas(List<Vendas> vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + getId() + ", nome =" + getNome() + "CPF =" + cpf + "]";
    }
}
