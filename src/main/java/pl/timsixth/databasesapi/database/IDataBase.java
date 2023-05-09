package pl.timsixth.databasesapi.database;

import pl.timsixth.databasesapi.database.async.IAsyncQuery;

import java.sql.SQLException;

/**
 * Represents every database
 */
public interface IDataBase {

    /**
     * Opens connection
     *
     * @throws SQLException when can not open connection (Only for SQL database)
     */
    default void openConnection() throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Closes connection
     *
     * @throws SQLException when can not close connection (Only for SQL database)
     */
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

    /**
     * @return interface to executing async queries
     */
    IAsyncQuery getAsyncQuery();

}
