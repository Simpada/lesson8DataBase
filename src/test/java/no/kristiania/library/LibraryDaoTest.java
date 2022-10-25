package no.kristiania.library;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public interface LibraryDaoTest {
    @Test
    void shouldRetrieveSavedLibrary() throws SQLException;
}
