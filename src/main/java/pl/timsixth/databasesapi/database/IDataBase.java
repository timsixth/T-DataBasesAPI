package pl.timsixth.databasesapi.database;

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

}
