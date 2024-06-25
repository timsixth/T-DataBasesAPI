package pl.timsixth.databasesapi.database;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class DatabaseConnector {

    /**
     * Connects to database
     *
     * @param dataBase database to connect
     * @throws SQLException           when can not connect to database
     * @throws IOException            when can not create file
     * @throws ClassNotFoundException when can not find database driver
     */
    public static void connect(IDataBase dataBase) throws SQLException, IOException, ClassNotFoundException {
        if (dataBase instanceof ISQLite) {
            ISQLite sqlLite = (ISQLite) dataBase;

            File database = sqlLite.createDataBase(dataBase.getDataBase() + ".db");
            sqlLite.openConnection(database);
            return;
        }

        dataBase.openConnection();
    }
}
