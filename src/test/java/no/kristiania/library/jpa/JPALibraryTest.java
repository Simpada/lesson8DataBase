package no.kristiania.library.jpa;

import jakarta.persistence.EntityManager;
import no.kristiania.library.AbstractLibraryDaoTest;
import no.kristiania.library.TestEntityManager;

public class JPALibraryTest extends AbstractLibraryDaoTest {

    private final EntityManager entityManager;

    protected JPALibraryTest(@TestEntityManager EntityManager entityManager) {
        super(new JPALibraryDao(entityManager));
        this.entityManager = entityManager;
    }

    @Override
    protected void flush() {
        entityManager.flush();
        entityManager.clear();
    }
}
