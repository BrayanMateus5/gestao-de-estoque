package projetomadeira.poo;

public class TesteDeConexao {
    public static void main(String[] args) {
        try{
        EntityManagerFactory emf = Persistence.creaeteEntityManagerFactory("persistenciaPU");
            EntityManager em = emf.createEntityManager();
            System.out.println("Tá feita a conexão");
            em.close();
            emf.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
