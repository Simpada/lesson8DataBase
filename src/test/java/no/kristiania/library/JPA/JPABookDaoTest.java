package no.kristiania.library.JPA;

import jakarta.persistence.EntityManager;
import no.kristiania.library.AbstractBookDaoTest;
import no.kristiania.library.TestEntityManager;
import no.kristiania.library.jpa.JPABookDao;

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
