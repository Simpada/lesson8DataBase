package no.kristiania.library.jdbc;

import no.kristiania.library.AbstractLibraryDaoTest;
import no.kristiania.library.InMemoryDataSource;

public class JDBCLibraryDaoTest extends AbstractLibraryDaoTest {

    public JDBCLibraryDaoTest() {
        super(new JDBCLibraryDao(InMemoryDataSource.createTestDataSource()));
    }

}
