package pl.timsixth.databasesapi.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.timsixth.databasesapi.config.ConfigFileSpigot;
import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.DataBaseType;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.spigot.DatabasesApiPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTests {
    private static ISQLDataBase mysql;


    @Before
    public void init() throws SQLException {
        mysql = new MySQL("localhost", "root", "", "servertestowy", 3306);
        mysql.openConnection();
        //Mockito.mock(Bukkit.class);
    }

    @After
    public void runAfterTests() {
    }

    @Test
    public void shouldConnectToMysql() {
        try {
            mysql.openConnection();
            System.out.println("Successful connected to MySQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldConnectDatabaseInConfig() {
        IConfigFile configFile = new ConfigFileSpigot(DatabasesApiPlugin.getInstance());
        try {
            if (configFile.getDataBaseType("MYSQL") == DataBaseType.MYSQL) {
                mysql.openConnection();
                System.out.println("Successful connected to MySQL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldCloseConnectionFromMysql() {
        try {
            mysql.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldGetConnection() {
        mysql.getConnection();
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
        mysql.getTableCreator().createColumn("test123", DataType.VARCHAR, 0, false)
                .create("test12");
    }
}
