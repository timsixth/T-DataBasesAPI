package pl.timsixth.databasesapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import pl.timsixth.databasesapi.api.IDataBasesApi;
import pl.timsixth.databasesapi.config.ConfigFileSpigot;
import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.DatabaseConnector;
import pl.timsixth.databasesapi.database.DatabaseFactory;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.migration.DataBaseMigrations;
import pl.timsixth.databasesapi.database.migration.Migrations;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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


    @Override
    public void onEnable() {
        configFile = new ConfigFileSpigot(this);
        dataBasesApi = new DatabasesAPI(this);
        dataBaseMigrations = new DataBaseMigrations();
        migrations = new Migrations(dataBaseMigrations);

        getConfig().options().copyDefaults(true);
        saveConfig();

        try {
            currentSQLDataBase = (ISQLDataBase) DatabaseFactory.createDatabase(configFile.getDataBaseType(), this);
            DatabaseConnector.connect(currentSQLDataBase);

            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successful connected to " + configFile.getDataBaseType());

            dataBaseMigrations.createMigrationsTable();
            dataBaseMigrations.checkMigrations();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Bukkit.getLogger().severe(ex.getMessage());
        }
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
