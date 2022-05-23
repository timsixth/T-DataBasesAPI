package pl.timsixth.databasesapi.tests;

import org.junit.Test;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SQLiteTests {

    private final ISQLite sqLite = new SQLite();

    @Test
    public void shouldConnectToSqlite() throws IOException, SQLException, ClassNotFoundException {
        File file = new File("database.db");
        if (!file.exists()) {
            file.createNewFile();
        }
        sqLite.openConnection(file);
    }

    @Test
    public void shouldExecuteQuery() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS test(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Name varchar(36) not null)";
        sqLite.query(sql).executeUpdate();
    }
}
