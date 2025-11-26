package projetomadeira.poo.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    // parte do JPA

    // Nome da unidade
    private static final String PERSISTENCE_UNIT_NAME = "persistenciaPU";

    // Cria uma única e final EntityManagerFactory
    private static final EntityManagerFactory emf;

    static { // só quando o bloco é carregado
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            System.err.println("Falha ao criar EntityManagerFactory: " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
