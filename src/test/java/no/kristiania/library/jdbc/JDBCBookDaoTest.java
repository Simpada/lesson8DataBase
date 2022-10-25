package no.kristiania.library.jdbc;

import no.kristiania.library.AbstractBookDaoTest;
import no.kristiania.library.InMemoryDataSource;

public class JDBCBookDaoTest extends AbstractBookDaoTest {
    protected JDBCBookDaoTest() {
        super(new JDBCBookDao(InMemoryDataSource.createTestDataSource()));
    }
}
