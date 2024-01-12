package pl.timsixth.databasesapi;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import pl.timsixth.databasesapi.api.IDataBasesApi;
import pl.timsixth.databasesapi.config.ConfigFileSpigot;
import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.migration.DataBaseMigrations;
import pl.timsixth.databasesapi.database.migration.Migrations;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.io.File;

public final class DatabasesApiPlugin extends JavaPlugin {
    ISQLDataBase currentSQLDataBase;
    IConfigFile configFile;
    Migrations migrations;
    DataBaseMigrations dataBaseMigrations;
    private static IDataBasesApi dataBasesApi;

    public DatabasesApiPlugin() {
    }

    public DatabasesApiPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        configFile = new ConfigFileSpigot(this);
        dataBasesApi = new DatabasesAPI(this);
        dataBaseMigrations = new DataBaseMigrations();
        migrations = new Migrations(dataBaseMigrations);

        getConfig().options().copyDefaults(true);
        saveConfig();

        switch (configFile.getDataBaseType()) {
            case MYSQL:
                ISQLDataBase mysql = new MySQL();
                mysql.setDataBase(getConfig().getString("database"));
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
                sqlite.setDataBase(getConfig().getString("database"));
                File database = sqlite.createDataBase(sqlite.getDataBase() + ".db");
                sqlite.openConnection(database);
                currentSQLDataBase = sqlite;
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successful connected to SQLite");
                break;
            default:
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "This database doesn't exists");
        }

        dataBaseMigrations.createMigrationsTable();
        dataBaseMigrations.checkMigrations();
    }

    @Override
    public void onDisable() {
        currentSQLDataBase.closeConnection();

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successful closed connection");

        migrations.unload();
        dataBaseMigrations.unload();
    }

    /**
     * @return databaseApi which included config and currentSQLDatabase
     */
    public static IDataBasesApi getApi() {
        return dataBasesApi;
    }
}
