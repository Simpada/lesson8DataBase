package no.kristiania.library;

import no.kristiania.library.jdbc.JDBCLibraryDao;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractLibraryDaoTest {

    private final LibraryDao dao;

    protected AbstractLibraryDaoTest(LibraryDao dao) {
        this.dao = dao;
    }

    @Test
    void shouldRetrieveSavedLibrary() throws SQLException {
        var library = SampleData.sampleLibrary();
        dao.save(library);
        flush();
        assertThat(dao.retrieve(library.getId()))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .isEqualTo(library)
                .isNotSameAs(library)
        ;
    }

    protected void flush(){

    }

}
