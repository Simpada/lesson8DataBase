package no.kristiania.library;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public interface PhysicalBookDaoTest {
    @Test
    void shouldListBooksByLibrary() throws SQLException;
}
