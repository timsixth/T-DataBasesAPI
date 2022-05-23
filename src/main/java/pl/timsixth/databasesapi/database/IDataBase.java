package pl.timsixth.databasesapi.database;

import pl.timsixth.databasesapi.database.async.IAsyncQuery;

import java.sql.SQLException;

public interface IDataBase {

    default void openConnection() throws SQLException {
    }

    void closeConnection() throws SQLException;

    String getHostname();

    String getUsername();

    String getPassword();

    String getDataBase();

    int getPort();

    void setHostname(String hostname);

    void setPort(int port);

    void setPassword(String password);

    void setUsername(String username);

    void setDatabase(String dataBase);

    IAsyncQuery getAsyncQuery();

}
