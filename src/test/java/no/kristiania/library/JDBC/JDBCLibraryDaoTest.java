package no.kristiania.library.JDBC;

import no.kristiania.library.AbstractLibraryDaoTest;
import no.kristiania.library.InMemoryDataSource;
import no.kristiania.library.jdbc.JDBCLibraryDao;

public class JDBCLibraryDaoTest extends AbstractLibraryDaoTest {

    public JDBCLibraryDaoTest() {
        super(new JDBCLibraryDao(InMemoryDataSource.createTestDataSource()));
    }

}
