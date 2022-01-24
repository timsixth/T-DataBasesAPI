package pl.timsixth.databasesapi.tests;

import org.junit.Test;
import pl.timsixth.databasesapi.config.ConfigFileSpigot;
import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.spigot.DatabasesAPISpigot;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTests {

    public MySQLTests() {
    }

    private final ISQLDataBase mysql = new MySQL("localhost", "root", "", "servertestowy", 3306);

    @Test
    public void should_connect_to_mysql() {
        try {
            mysql.openConnection();
            System.out.println("Successful connected to MySQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_connect_database_in_config() {
        IConfigFile configFile = new ConfigFileSpigot(DatabasesAPISpigot.getInstance());
        try {
            switch (configFile.getDataBaseType("MYSQL")) {
                case MYSQL:
                    mysql.openConnection();
                    System.out.println("Successful connected to MySQL");
                    break;
                case SQLITE:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_close_connection_from_mysql() {
        try {
            mysql.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_get_connection() {
        mysql.getConnection();
    }

    @Test
    public void should_execute_query() throws SQLException {
        String sql = "SELECT * FROM minipvp_stats";
        ResultSet resultSet = mysql.query(sql).executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("UUID"));
        }

    }

}
