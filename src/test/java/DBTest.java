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


    private Book sampleBook() {
        var book = new Book();
        book.setTitle("Java in a Nutshell");
        book.setAuthor("Benjamin Evans and David Flanagan");
        book.setYear(2018);
        return book;
    }

}
