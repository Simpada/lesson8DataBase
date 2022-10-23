import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class DBTest {


    private BookDao dao;


    @BeforeEach
    void setUp() throws SQLException {
        var dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testDatabase;DB_CLOSE_DELAY=-1");
        try (var connection = dataSource.getConnection()){
            var statement = connection.createStatement();
            statement.executeUpdate("create table books (id serial primary key, title varchar(100), author varchar(100))");
        }
        dao = new BookDao(dataSource);
    }

    @Test
    void shouldRetrieveSavedBook() throws SQLException {
        var book = sampleBook();
        dao.save(book);
        assertThat(dao.retrieve(book.getId()))
                .usingRecursiveComparison()
                .isEqualTo(book)
                //.isNotSameAs(book)
                ;
    }

    @Test
    void shouldFindBooksByAuthorName() throws SQLException {
        var book = sampleBook();
        var bookWithSameAuthor = sampleBook();
        bookWithSameAuthor.setAuthor(book.getAuthor());
        var bookWithOtherAuthor = sampleBook();
        bookWithOtherAuthor.setAuthor("Other Author");

        dao.save(book);
        dao.save(bookWithSameAuthor);
        dao.save(bookWithOtherAuthor);

        assertThat(dao.findByAuthorName(book.getAuthor()))
                .extracting(Book::getId)
                .contains(book.getId(), bookWithSameAuthor.getId())
                .doesNotContain(bookWithOtherAuthor.getId());
    }

    private Book sampleBook() {
        var book = new Book();
        book.setTitle("Java in a Nutshell");
        book.setAuthor("Benjamin Evans and David Flanagan");
        book.setYear(2018);
        return book;
    }

}
