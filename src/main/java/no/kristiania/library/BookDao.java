package no.kristiania.library;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    void save(Book book) throws SQLException;

    Book retrieve(long id) throws SQLException;

    List<Book> findByAuthorName(String authorName) throws SQLException;
}
