package no.kristiania.library.jdbc;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import no.kristiania.library.Book;
import no.kristiania.library.BookDao;

public class JDBCBookDao implements BookDao {

    private final DataSource dataSource;

    public JDBCBookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Book book) throws SQLException {
        try (var connection = dataSource.getConnection()) {
            var sql = "insert into books (title, author, release_year) values (?,?,?)";
            try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setInt(3, book.getYear());
                statement.executeUpdate();

                try (var generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    book.setId(generatedKeys.getLong("id"));
                }
            }
        }
    }

    @Override
    public Book retrieve(long id) throws SQLException {

        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.prepareStatement("select * from books where id = ?")) {
                statement.setLong(1, id);
                try(var rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return readBooks(rs);
                    } else {
                        return null;
                    }
                }
            }
        }
    }


    @Override
    public List<Book> findByAuthorName(String authorName) throws SQLException {

        try (var connection = dataSource.getConnection()) {
            try(var statement = connection.prepareStatement("select * from books where author = ?")) {
                statement.setString(1, authorName);
                try (var rs = statement.executeQuery()) {
                    var books = new ArrayList<Book>();
                    while (rs.next()) {
                        books.add(readBooks(rs));
                    }
                    return books;
                }
            }
        }
    }

    public static Book readBooks(ResultSet rs) throws SQLException {

        var book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("release_year"));
        return book;

    }

}
