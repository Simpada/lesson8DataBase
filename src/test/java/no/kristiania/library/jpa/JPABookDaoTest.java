package no.kristiania.library.jpa;

import jakarta.persistence.EntityManager;
import no.kristiania.library.AbstractBookDaoTest;
import no.kristiania.library.TestEntityManager;

public class JPABookDaoTest extends AbstractBookDaoTest {

    private final EntityManager entityManager;

    protected JPABookDaoTest(@TestEntityManager EntityManager entityManager) {
        super(new JPABookDao(entityManager));
        this.entityManager = entityManager;
    }

    @Override
    protected void flush() {
        entityManager.flush();
        entityManager.clear();
    }
}
