package no.kristiania.library;

import no.kristiania.library.jdbc.JDBCBookDao;
import no.kristiania.library.jdbc.JDBCLibraryDao;
import no.kristiania.library.jdbc.JDBCPhysicalBookDao;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class PhysicalBookDaoTest {


    private final DataSource dataSource = InMemoryDataSource.createTestDataSource();
    private final JDBCLibraryDao libraryDao = new JDBCLibraryDao(dataSource);
    private final JDBCBookDao bookDao = new JDBCBookDao(dataSource);
    private final JDBCPhysicalBookDao dao = new JDBCPhysicalBookDao(dataSource);

    @Test
    void shouldListBooksByLibrary() throws SQLException {
        var firstBook = SampleData.sampleBook();
        var secondBook = SampleData.sampleBook();
        bookDao.save(firstBook);
        bookDao.save(secondBook);

        var firstLibrary = SampleData.sampleLibrary();
        var secondLibrary = SampleData.sampleLibrary();
        libraryDao.save(firstLibrary);
        libraryDao.save(secondLibrary);

        dao.insert(firstLibrary, firstBook);
        dao.insert(firstLibrary, secondBook);
        dao.insert(secondLibrary, firstBook);

        assertThat(dao.findByLibrary(firstLibrary.getId()))
                .extracting(Book::getId)
                .contains(firstBook.getId(), secondBook.getId());
        assertThat(dao.findByLibrary(secondLibrary.getId()))
                .extracting(Book::getId)
                .contains(firstBook.getId())
                .doesNotContain(secondBook.getId());
    }

}
