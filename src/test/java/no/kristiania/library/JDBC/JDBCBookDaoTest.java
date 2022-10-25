package no.kristiania.library.JDBC;

import no.kristiania.library.AbstractBookDaoTest;
import no.kristiania.library.BookDao;
import no.kristiania.library.InMemoryDataSource;
import no.kristiania.library.jdbc.JDBCBookDao;

public class JDBCBookDaoTest extends AbstractBookDaoTest {
    protected JDBCBookDaoTest() {
        super(new JDBCBookDao(InMemoryDataSource.createTestDataSource()));
    }
}
