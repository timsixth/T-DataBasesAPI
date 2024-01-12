package pl.timsixth.databasesapi.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.structure.datatype.DataTypes;
import pl.timsixth.databasesapi.database.structure.datatype.VarcharDataType;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class SQLiteTests {

    private final ISQLite sqLite = new SQLite();
    private File file;

    @Before
    public void init() throws IOException {
        file = new File("database.db");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @After
    public void tearDown() {
        file.delete();

        sqLite.closeConnection();
    }

    @Test
    public void shouldConnectToSqlite() throws SQLException, ClassNotFoundException {
        sqLite.openConnection(file);

        assertNotNull(sqLite.getConnection());
    }

    @Test
    public void shouldExecuteQuery() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS test(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Name varchar(36) not null)";
        int rowCount = sqLite.query(sql).executeUpdate();

        assertEquals(0, rowCount);
    }

    @Test
    public void shouldCreateTableWithCreator() {
        boolean created = sqLite.getTableCreator()
                .id()
                .createColumn("name", new VarcharDataType(40), true)
                .defaultValue("name", "kokos")
                .createColumn("data", DataTypes.DATE, false)
                .createColumn("boolean", DataTypes.BOOLEAN, false)
                .createTable("test4");

        assertTrue(created);
    }

}
