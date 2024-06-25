package pl.timsixth.databasesapi.database;

import org.bukkit.configuration.file.FileConfiguration;
import pl.timsixth.databasesapi.DatabasesApiPlugin;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.database.type.SQLite;

public final class DatabaseFactory {

    /**
     * Creates database instance
     *
     * @param dataBaseType       type from config.yml
     * @param databasesApiPlugin database api plugin, to get config
     * @return created database instance
     */
    public static IDataBase createDatabase(DataBaseType dataBaseType, DatabasesApiPlugin databasesApiPlugin) {
        FileConfiguration fileConfiguration = databasesApiPlugin.getConfig();

        if (dataBaseType == DataBaseType.MYSQL) {
            ISQLDataBase mysql = new MySQL();
            mysql.setDataBase(fileConfiguration.getString("database"));
            mysql.setHostname(fileConfiguration.getString("hostname"));
            mysql.setPassword(fileConfiguration.getString("password"));
            mysql.setPort(fileConfiguration.getInt("port"));
            mysql.setUsername(fileConfiguration.getString("username"));

            return mysql;
        }

        ISQLite sqlite = new SQLite(databasesApiPlugin);
        sqlite.setDataBase(fileConfiguration.getString("database"));

        return sqlite;
    }
}
