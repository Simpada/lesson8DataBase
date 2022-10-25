package no.kristiania.library.jpa;

import jakarta.persistence.EntityManager;
import no.kristiania.library.Library;
import no.kristiania.library.LibraryDao;

import java.sql.SQLException;

public class JPALibraryDao implements LibraryDao {

    private final EntityManager entityManager;

    public JPALibraryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Library library){
        entityManager.persist(library);
    }

    @Override
    public Library retrieve(long id){
        return entityManager.find(Library.class, id);
    }
}
