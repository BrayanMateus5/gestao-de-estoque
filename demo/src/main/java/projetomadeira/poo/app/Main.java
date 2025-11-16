package projetomadeira.poo.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import projetomadeira.poo.entidade.Cliente;
import projetomadeira.poo.entidade.Fornecedor;
import projetomadeira.poo.entidade.Produto;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");
        EntityManager em = emf.createEntityManager();

        System.out.println("Gestão de Estoque Iniciada");

        Produto produtoTeste = new Produto("Burguesa", "Cerveja Pilsen", 2.50,
                3.99, 24.0, 12);
        Cliente clienteTeste = new Cliente("Marcelo", "MarceloEmail@gmail.com", "095.784.478-88");
        Fornecedor fornecedorTeste = new Fornecedor("Casa de Conti", "contato@casadeconti.com", "07.526.557/0005-33");

        try {
            em.getTransaction().begin();
            em.persist(produtoTeste);
            em.persist(clienteTeste);
            em.persist(fornecedorTeste);
            em.getTransaction().commit();
            System.out.println("Produto salvo com sucesso no ID de : " + produtoTeste.getId());
            System.out.println("Cliente salvo com sucesso no ID de : " + clienteTeste.getId());
            System.out.println("Fornecedor salvo com sucesso no ID de : " + fornecedorTeste.getId());

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao salvar o produto: " + e.getMessage());
            System.err.println("Erro ao salvar o cliente: " + e.getMessage());
            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}
