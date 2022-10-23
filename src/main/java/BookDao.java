import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private final DataSource dataSource;

    public BookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    public Book retrieve(Long id) throws SQLException {

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
