package pl.timsixth.databasesapi.database;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface ISQLite extends ISQLDataBase{

    File createDataBase(String name) throws IOException;
    void openConnection(File file) throws SQLException, ClassNotFoundException;
}
