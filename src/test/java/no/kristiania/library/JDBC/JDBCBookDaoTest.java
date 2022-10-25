package no.kristiania.library.JDBC;

import no.kristiania.library.Book;
import no.kristiania.library.BookDaoTest;
import no.kristiania.library.InMemoryDataSource;
import no.kristiania.library.SampleData;
import no.kristiania.library.jdbc.JDBCBookDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class JDBCBookDaoTest implements BookDaoTest {

    private final JDBCBookDao dao = new JDBCBookDao(InMemoryDataSource.createTestDataSource());

    @Override
    @Test
    public void shouldRetrieveSavedBook() throws SQLException {
        var book = SampleData.sampleBook();
        dao.save(book);
        assertThat(dao.retrieve(book.getId()))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(book)
                .isNotSameAs(book)
                ;
    }

    @Override
    @Test
    public void shouldFindBooksByAuthorName() throws SQLException {
        var book = SampleData.sampleBook();
        var bookWithSameAuthor = SampleData.sampleBook();
        bookWithSameAuthor.setAuthor(book.getAuthor());
        var bookWithOtherAuthor = SampleData.sampleBook();
        bookWithOtherAuthor.setAuthor("Other Author");

        dao.save(book);
        dao.save(bookWithSameAuthor);
        dao.save(bookWithOtherAuthor);

        assertThat(dao.findByAuthorName(book.getAuthor()))
                .extracting(Book::getId)
                .contains(book.getId(), bookWithSameAuthor.getId())
                .doesNotContain(bookWithOtherAuthor.getId());
    }

    @Override
    @Test
    public void shouldRetrieveNullForMissingBook() throws SQLException {
        assertThat(dao.retrieve(-1L)).isNull();
    }

}
