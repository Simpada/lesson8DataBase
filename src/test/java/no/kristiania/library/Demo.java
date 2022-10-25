package no.kristiania.library;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import no.kristiania.library.jpa.JPABookDao;
import no.kristiania.library.jpa.JPALibraryDao;
import org.eclipse.jetty.plus.jndi.Resource;


import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

public class Demo {

    private final BookDao bookDao;
    private final LibraryDao libraryDao;
    private final EntityManager entityManager;
    //private final PhysicalBookDao physicalBookDao;

    public Demo(EntityManager entityManager) {
        this.bookDao = new JPABookDao(entityManager);
        this.libraryDao = new JPALibraryDao(entityManager);

        this.entityManager = entityManager;
    }



    public static void main(String[] args) throws SQLException, IOException, NamingException {

        new Resource("jdbc/dataSource", Database.getDataSource());
        var entityManager = Persistence
                .createEntityManagerFactory("library")
                .createEntityManager();
        new Demo(entityManager).run();
    }

    private void run() throws SQLException {

        entityManager.getTransaction().begin();

        var book = SampleData.sampleBook();
        bookDao.save(book);
        var library = SampleData.sampleLibrary();
        libraryDao.save(library);

        entityManager.flush();
        entityManager.getTransaction().commit();
        //physicalBookDao.insert(library, book);
    }

}
