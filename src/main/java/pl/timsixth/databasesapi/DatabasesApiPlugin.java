package pl.timsixth.databasesapi;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import pl.timsixth.databasesapi.api.IDataBasesApi;
import pl.timsixth.databasesapi.config.ConfigFileSpigot;
import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.io.File;

public final class DatabasesApiPlugin extends JavaPlugin {
    private ISQLDataBase currentSQLDataBase;
    private IConfigFile configFile;

    private static IDataBasesApi dataBasesApi;

    @SneakyThrows
    @Override
    public void onEnable() {
        configFile = new ConfigFileSpigot(this);
        dataBasesApi = new DatabasesAPI(this);
        getConfig().options().copyDefaults(true);
        saveConfig();
        switch (configFile.getDataBaseType()) {
            case MYSQL:
                ISQLDataBase mysql = new MySQL();
                mysql.setDatabase(getConfig().getString("database"));
                mysql.setHostname(getConfig().getString("hostname"));
                mysql.setPassword(getConfig().getString("password"));
                mysql.setPort(getConfig().getInt("port"));
                mysql.setUsername(getConfig().getString("username"));
                mysql.openConnection();
                currentSQLDataBase = mysql;
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successful connected to MySQL");
                break;
            case SQLITE:
                ISQLite sqlite = new SQLite(this);
                sqlite.setDatabase(getConfig().getString("database"));
                File database = sqlite.createDataBase(sqlite.getDataBase() + ".db");
                sqlite.openConnection(database);
                currentSQLDataBase = sqlite;
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successful connected to SQLite");
                break;
            default:
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "This database doesn't exists");

        }
    }

    public static IDataBasesApi getApi() {
        return dataBasesApi;
    }
    @RequiredArgsConstructor
    private static class DatabasesAPI implements IDataBasesApi {

        private final DatabasesApiPlugin databasesApiPlugin;

        @Override
        public IConfigFile getConfig() {
            return databasesApiPlugin.configFile;
        }

        @Override
        public ISQLDataBase getCurrentSqlDataBase() {
            return databasesApiPlugin.currentSQLDataBase;
        }
    }

}
