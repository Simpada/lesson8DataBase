package no.kristiania.library.jpa;

import jakarta.persistence.EntityManager;
import no.kristiania.library.Book;
import no.kristiania.library.BookDao;

import java.util.List;

public class JPABookDao implements BookDao {

    private final EntityManager entityManager;

    public JPABookDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Book book){
        entityManager.persist(book);
    }

    @Override
    public Book retrieve(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return entityManager.createNamedQuery("findByAuthor")
                .setParameter("author", authorName)
                .getResultList();
    }
}
