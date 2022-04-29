package pl.timsixth.databasesapi.spigot;

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

public final class DatabasesAPISpigot extends JavaPlugin {
    private static DatabasesAPISpigot databasesAPISpigot;
    private ISQLDataBase currentSQLDataBase;
    private IConfigFile configFile;

    private IDataBasesApi dataBasesApi;

    public DatabasesAPISpigot() {
        databasesAPISpigot = this;
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        configFile = new ConfigFileSpigot(this);
        dataBasesApi = new DatabasesAPI(this);
        getConfig().options().copyDefaults(true);
        saveConfig();
        switch (configFile.getDataBaseType()) {
            case MYSQL:
                ISQLDataBase mysql = new MySQL(getConfig().getString("hostname"),
                        getConfig().getString("username"),
                        getConfig().getString("password"),
                        getConfig().getString("database"),
                        getConfig().getInt("port"));
                mysql.openConnection();
                currentSQLDataBase = mysql;
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successful connected to MySQL");
                break;
            case SQLITE:
                ISQLite sqlite = new SQLite();
                sqlite.setDatabase(getConfig().getString("database"));
                File database = sqlite.createDataBase(sqlite.getDataBase() + ".db");
                sqlite.openConnection(database);
                currentSQLDataBase = sqlite;
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successful connected to SQLite");
                break;
            default:
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "This database dosent exists");

        }
    }

    public IDataBasesApi getApi() {
        return dataBasesApi;
    }

    @Deprecated
    public ISQLDataBase getCurrentSQLDataBase() {
        return currentSQLDataBase;
    }

    @Deprecated
    public static DatabasesAPISpigot getInstance() {
        return databasesAPISpigot;
    }

    @RequiredArgsConstructor
    private static class DatabasesAPI implements IDataBasesApi {

        private final DatabasesAPISpigot databasesAPISpigot;

        @Override
        public IConfigFile getConfig() {
            return databasesAPISpigot.configFile;
        }

        @Override
        public ISQLDataBase getCurrentSqlDataBase() {
            return databasesAPISpigot.currentSQLDataBase;
        }
    }

}
