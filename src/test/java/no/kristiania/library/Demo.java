package no.kristiania.library;

import com.zaxxer.hikari.HikariDataSource;
import no.kristiania.library.jdbc.JDBCBookDao;
import no.kristiania.library.jdbc.JDBCLibraryDao;
import no.kristiania.library.jdbc.JDBCPhysicalBookDao;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Demo {

    private final BookDao bookDao;
    private final LibraryDao libraryDao;
    private final PhysicalBookDao physicalBookDao;

    public Demo(DataSource dataSource){
        this.bookDao = new JDBCBookDao(dataSource);
        this.libraryDao = new JDBCLibraryDao(dataSource);
        this.physicalBookDao = new JDBCPhysicalBookDao(dataSource);
    }

    public static void main(String[] args) throws SQLException, IOException {

        var properties = new Properties();
        try (var reader = new FileReader("application.properties")) {
            properties.load(reader);
        }

        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        Flyway.configure().dataSource(dataSource).load().migrate();
        new Demo(dataSource).run();
    }

    private void run() throws SQLException {
        var book = SampleData.sampleBook();
        bookDao.save(book);
        var library = SampleData.sampleLibrary();
        libraryDao.save(library);

        physicalBookDao.insert(library, book);
    }

}
