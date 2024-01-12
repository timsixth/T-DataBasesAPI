package pl.timsixth.databasesapi.database;

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
     */
    void closeConnection();

    String getHostname();

    String getUsername();

    String getPassword();

    String getDataBase();

    int getPort();

    void setHostname(String hostname);

    void setPort(int port);

    void setPassword(String password);

    void setUsername(String username);

    void setDataBase(String dataBase);
}
