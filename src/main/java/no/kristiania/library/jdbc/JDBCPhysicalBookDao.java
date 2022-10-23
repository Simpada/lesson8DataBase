package no.kristiania.library.jdbc;

import no.kristiania.library.Book;
import no.kristiania.library.Library;
import no.kristiania.library.PhysicalBookDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPhysicalBookDao implements PhysicalBookDao {

    private final DataSource dataSource;
    
    
    public JDBCPhysicalBookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Library library, Book book) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var sql = "insert into physical_books (library_id, book_id) values (?,?)";
            try (var statement = connection.prepareStatement(sql)) {
                statement.setLong(1, library.getId());
                statement.setLong(2, book.getId());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public List<Book> findByLibrary(long libraryId) throws SQLException {

        try (var connection = dataSource.getConnection()) {
            var sql = """
                    select b.*
                    from physical_books pb join books b on pb.book_id = b.id
                    where library_id = ?
                    """;
            try (var statement = connection.prepareStatement(sql)) {
                statement.setLong(1, libraryId);
                try (var rs = statement.executeQuery()) {
                    var books = new ArrayList<Book>();
                    while (rs.next()) {
                        books.add(JDBCBookDao.readBooks(rs));
                    }
                    return books;
                }
            }
        }
    }
}
