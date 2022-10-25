package no.kristiania.library;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public interface BookDaoTest {
    @Test
    void shouldRetrieveSavedBook() throws SQLException;

    @Test
    void shouldFindBooksByAuthorName() throws SQLException;

    @Test
    void shouldRetrieveNullForMissingBook() throws SQLException;
}
