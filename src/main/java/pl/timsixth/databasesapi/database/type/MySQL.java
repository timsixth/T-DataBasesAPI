package pl.timsixth.databasesapi.database.type;

import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import pl.timsixth.databasesapi.database.AbstractSQLDataBase;
import pl.timsixth.databasesapi.database.structure.ITable;
import pl.timsixth.databasesapi.database.structure.mysql.MySqlTable;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@NoArgsConstructor
public class MySQL extends AbstractSQLDataBase {

    public MySQL(String hostname, String username, String password, String database, int port) {
        super(hostname, username, password, database, port);
    }

    @Override
    public void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + getHostname() + ":" + getPort() + "/" + getDataBase(), getUsername(),
                getPassword());
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
            Bukkit.getLogger().severe(e.getMessage());
        }

        return preparedStatement;
    }

    @Override
    public ITable getTableCreator() {
        return new MySqlTable(this);
    }
}
