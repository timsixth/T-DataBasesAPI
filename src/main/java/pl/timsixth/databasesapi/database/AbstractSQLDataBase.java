package pl.timsixth.databasesapi.database;

import org.bukkit.Bukkit;
import pl.timsixth.databasesapi.database.async.AsyncQueryImpl;
import pl.timsixth.databasesapi.database.async.IAsyncQuery;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractSQLDataBase extends AbstractDataBase implements ISQLDataBase {
    public AbstractSQLDataBase() {
    }

    public AbstractSQLDataBase(String hostname, String username, String password, String database, int port) {
        super(hostname, username, password, database, port);
    }

    protected Connection connection;

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            Bukkit.getLogger().severe(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public IAsyncQuery getAsyncQuery() {
        return new AsyncQueryImpl(this);
    }
}
