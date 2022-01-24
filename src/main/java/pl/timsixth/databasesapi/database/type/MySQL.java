package pl.timsixth.databasesapi.database.type;

import lombok.NoArgsConstructor;
import pl.timsixth.databasesapi.database.AbstractDataBase;
import pl.timsixth.databasesapi.database.ISQLDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@NoArgsConstructor
public class MySQL extends AbstractDataBase implements ISQLDataBase{

    public MySQL(String hostname, String username, String password, String database, int port) {
        super(hostname, username, password, database, port);
    }

    private static Connection connection;

    @Override
    public void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        connection = DriverManager.getConnection("jdbc:mysql://" + getHostname() + ":" + getPort() + "/" + getDataBase(), getUsername(),
                getPassword());
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                return;
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PreparedStatement query(String query) {
        PreparedStatement preparedStatement = null;
        try {

            if (connection == null) {
                openConnection();
            } else if (connection.isClosed()) {
                openConnection();
            }

            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
