package no.kristiania.library.JDBC;

import no.kristiania.library.InMemoryDataSource;
import no.kristiania.library.LibraryDaoTest;
import no.kristiania.library.SampleData;
import no.kristiania.library.jdbc.JDBCLibraryDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class JDBCLibraryDaoTest implements LibraryDaoTest {

    private final JDBCLibraryDao dao = new JDBCLibraryDao(InMemoryDataSource.createTestDataSource());

    @Override
    @Test
    public void shouldRetrieveSavedLibrary() throws SQLException {
        var library = SampleData.sampleLibrary();
        dao.save(library);
        assertThat(dao.retrieve(library.getId()))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(library)
                .isNotSameAs(library)
        ;
    }

}
