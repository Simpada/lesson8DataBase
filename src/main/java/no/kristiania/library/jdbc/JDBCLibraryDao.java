package no.kristiania.library.jdbc;

import no.kristiania.library.Library;
import no.kristiania.library.LibraryDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCLibraryDao implements LibraryDao {

    private final DataSource dataSource;

    public JDBCLibraryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Library library) throws SQLException {

        try(var connection = dataSource.getConnection()) {
            var sql = "insert into libraries (name, address) values (?,?)";
            try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, library.getName());
                statement.setString(2, library.getAddress());
                statement.executeUpdate();

                try (var generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    library.setId(generatedKeys.getLong("id"));
                }
            }
        }
    }

    @Override
    public Library retrieve(long id) throws SQLException {

        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.prepareStatement("select * from libraries where id = ?")) {
                statement.setLong(1, id);
                try (var rs = statement.executeQuery()) {
                    rs.next();
                    var library = new Library();
                    library.setId(rs.getLong("id"));
                    library.setName(rs.getString("name"));
                    library.setAddress(rs.getString("address"));
                    return library;
                }
            }
        }
    }
}
