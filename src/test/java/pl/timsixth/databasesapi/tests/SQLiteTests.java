package pl.timsixth.databasesapi.tests;

import org.junit.Before;
import org.junit.Test;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SQLiteTests {

    private final ISQLite sqLite = new SQLite();

    @Before
    public void init() throws IOException, SQLException, ClassNotFoundException {
        File file = new File("database.db");
        if (!file.exists()) {
            file.createNewFile();
        }
        sqLite.openConnection(file);
    }

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
    @Test
    public void shouldCreateTableWithCreator() throws SQLException {
        sqLite.getTableCreator().createColumn("id", DataType.INTEGER, 11, false)
                .primaryKey("id",true)
                .autoIncrement("id",true)
                .createColumn("name",DataType.VARCHAR,40,true)
                .defaultValue("name","kokos")
                .createColumn("data",DataType.DATE,0,false)
                .createColumn("boolean",DataType.BOOLEAN,0,false)
                .create("test4");
    }

}
