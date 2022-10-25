package no.kristiania.library.JPA;

import jakarta.persistence.EntityManager;
import no.kristiania.library.Book;
import no.kristiania.library.BookDao;

import java.sql.SQLException;
import java.util.List;

public class JPABookDao implements BookDao {



    public JPABookDao(EntityManager entityManager) {

    }

    @Override
    public void save(Book book) throws SQLException {

    }

    @Override
    public Book retrieve(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Book> findByAuthorName(String authorName) throws SQLException {
        return null;
    }
}
