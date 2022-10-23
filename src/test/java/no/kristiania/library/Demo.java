package no.kristiania.library;

import no.kristiania.library.jdbc.JDBCBookDao;
import no.kristiania.library.jdbc.JDBCLibraryDao;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Demo {

    private final JDBCBookDao bookDao;
    private final JDBCLibraryDao libraryDao;

    public Demo(DataSource dataSource){
        this.bookDao = new JDBCBookDao(dataSource);
        this.libraryDao = new JDBCLibraryDao(dataSource);
    }

    public static void main(String[] args) throws SQLException {
        var dataSource = new PGSimpleDataSource();
        dataSource.setURL("no.kristiania.library.jdbc:postgresql://localhost:5432/testdb");
        dataSource.setUser("test");
        dataSource.setPassword("super_safe_password");
        Flyway.configure().dataSource(dataSource).load().migrate();
        new Demo(dataSource).run();
    }

    private void run() throws SQLException {
        bookDao.save(SampleData.sampleBook());
        libraryDao.save(SampleData.sampleLibrary());
    }

}
