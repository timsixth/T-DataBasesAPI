package pl.timsixth.databasesapi.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.type.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class MySQLTests {
    private static ISQLDataBase mysql;

    @Before
    public void init() throws SQLException {
        mysql = new MySQL("localhost", "root", "", "servertestowy", 3306);
        mysql.openConnection();
    }
    @Test
    public void shouldExecuteSyncSelectQuery() throws SQLException {
        String sql = "SELECT Username FROM test WHERE id = 3";
        ResultSet resultSet = mysql.query(sql).executeQuery();

        while (resultSet.next()) {
            Assert.assertEquals("user1", resultSet.getString("Username"));
        }
    }

    @Test
    public void shouldExecuteSyncInsertQuery() throws SQLException {
        int amountOfRecords = mysql.query("INSERT INTO test VALUES(null,'user','12345')").executeUpdate();
        Assert.assertEquals(1, amountOfRecords);
    }

    @Test
    public void shouldCreateTableWithCreator() throws SQLException {
        mysql.getTableCreator()
                .createColumn("id", DataType.INT, 11, false)
                .primaryKey("id", true)
                .autoIncrement("id", true)
                .createColumn("name", DataType.VARCHAR, 40, true)
                .defaultValue("name", "kokos")
                .createColumn("data", DataType.DATE, 0, false)
                .createColumn("boolean", DataType.BOOLEAN, 0, false)
                .create("test1");
    }

    @Test
    public void shouldExecuteAsyncSelectQuery() throws ExecutionException, InterruptedException, SQLException {
        ResultSet resultSet = mysql.getAsyncQuery().query("SELECT Username FROM test WHERE id = 3");
        while (resultSet.next()){
            Assert.assertEquals("user1",resultSet.getString("Username"));
        }
    }

}
