package pl.timsixth.databasesapi.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.structure.datatype.DataTypes;
import pl.timsixth.databasesapi.database.structure.datatype.VarcharDataType;
import pl.timsixth.databasesapi.database.type.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class MySQLTests {
    private static ISQLDataBase mysql;

    @Before
    public void init() throws SQLException {
        mysql = new MySQL("localhost", "root", "", "testing", 3306);
        mysql.openConnection();

        mysql.getTableCreator()
                .id()
                .createColumn("Username", new VarcharDataType(40), false)
                .createColumn("age", DataTypes.INT, false)
                .createTable("test");

        mysql.query("INSERT INTO test VALUES(null,'user1', 12)").executeUpdate();
    }

    @After
    public void tearDown() throws SQLException {

        mysql.query("DROP TABLE IF EXISTS test").executeUpdate();
        mysql.query("DROP TABLE IF EXISTS test1").executeUpdate();

        mysql.closeConnection();
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
    public void shouldCreateTableWithCreator() {
        boolean created = mysql.getTableCreator()
                .id()
                .createColumn("name", new VarcharDataType(40), true)
                .defaultValue("name", "kokos")
                .createColumn("data", DataTypes.DATE, false)
                .createColumn("boolean", DataTypes.BOOLEAN, false)
                .createTable("test1");

        Assert.assertTrue(created);
    }

    @Test
    public void shouldExecuteAsyncSelectQuery() throws ExecutionException, InterruptedException, SQLException {
        ResultSet resultSet = mysql.getAsyncQuery().query("SELECT Username FROM test WHERE id = 3");
        while (resultSet.next()) {
            Assert.assertEquals("user1", resultSet.getString("Username"));
        }
    }

}
