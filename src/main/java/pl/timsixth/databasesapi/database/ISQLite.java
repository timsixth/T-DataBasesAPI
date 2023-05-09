package pl.timsixth.databasesapi.database;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Represents the SQLite database
 */
public interface ISQLite extends ISQLDataBase{

    /**
     * Creates SQLite database file with extension .db
     * @param name file name
     * @return created File with extension .db
     * @throws IOException when can not create file
     */
    File createDataBase(String name) throws IOException;

    /**
     * Opens connection to SQLite database
     * @param file database file
     * @throws SQLException when can not open connection
     * @throws ClassNotFoundException when couldn't find class
     */
    void openConnection(File file) throws SQLException, ClassNotFoundException;
}
