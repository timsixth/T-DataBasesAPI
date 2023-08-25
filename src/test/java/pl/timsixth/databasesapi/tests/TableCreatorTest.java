package pl.timsixth.databasesapi.tests;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.structure.datatype.DataTypes;
import pl.timsixth.databasesapi.database.structure.datatype.VarcharDataType;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class TableCreatorTest {

    private ISQLDataBase mysql;
    private ISQLite sqLite;

    @SneakyThrows
    @Before
    public void init() {
        mysql = new MySQL("localhost", "root", "", "testing", 3306);
        mysql.openConnection();

        sqLite = new SQLite();
        sqLite.openConnection(new File("database.db"));
    }

    @Test
    public void shouldCreateTableInMySQL() {
        boolean success = mysql.getTableCreator()
                .id()
                .createColumn("name", new VarcharDataType(20), false)
                .defaultValue("name", "test12")
                .createColumn("date1", DataTypes.DATE, false)
                .defaultValue("date1", "2020-10-09")
                .createColumn("datetime1", DataTypes.DATETIME, false)
                .defaultValue("datetime1", "2020-11-01 20:10")
                .createColumn("boolean1", DataTypes.BOOLEAN, false)
                .defaultValue("boolean1", true)
                .createColumn("double1", DataTypes.DOUBLE, false)
                .defaultValue("double1", 20.3)
                .createTable("test2");

        assertTrue(success);
    }


    @Test
    public void shouldCreateTableInSQLite() {
        boolean success = sqLite.getTableCreator()
                .id()
                .createColumn("name", new VarcharDataType(20), false)
                .defaultValue("name", "test12")
                .createColumn("date1", DataTypes.DATE, false)
                .defaultValue("date1", "2020-10-09")
                .createColumn("datetime1", DataTypes.DATETIME, false)
                .defaultValue("datetime1", "2020-11-01 20:10")
                .createColumn("boolean1", DataTypes.BOOLEAN, false)
                .defaultValue("boolean1", true)
                .createColumn("double1", DataTypes.DOUBLE, false)
                .defaultValue("double1", 20.3)
                .createTable("test2");

        assertTrue(success);
    }
}
