import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Demo {

    private final BookDao bookDao;
    private final LibraryDao libraryDao;

    public Demo(DataSource dataSource){
        this.bookDao = new BookDao(dataSource);
        this.libraryDao = new LibraryDao(dataSource);
    }

    public static void main(String[] args) throws SQLException {
        var dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/testdb");
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
