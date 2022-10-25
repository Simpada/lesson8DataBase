package no.kristiania.library.JPA;

import jakarta.persistence.EntityManager;
import no.kristiania.library.AbstractBookDaoTest;

public class JPABookDaoTest extends AbstractBookDaoTest {

    private final EntityManager entityManager;

    protected JPABookDaoTest(EntityManager entityManager) {
        super(new JPABookDao(entityManager));
        this.entityManager = entityManager;
    }

    @Override
    protected void flush() {
        entityManager.flush();
        entityManager.clear();
    }
}
