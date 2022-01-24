package pl.timsixth.databasesapi.bungee;

import lombok.SneakyThrows;
import net.md_5.bungee.api.plugin.Plugin;
import pl.timsixth.databasesapi.config.ConfigFileBungee;
import pl.timsixth.databasesapi.config.IConfigFileBungee;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.io.File;

public final class DatabasesAPIBungee extends Plugin {

    @SneakyThrows
    @Override
    public void onEnable() {
        String name = "config.yml";
        IConfigFileBungee configFile = new ConfigFileBungee(this);
        configFile.createFile(name);

        switch (configFile.getDataBaseType()) {
            case MYSQL:
                ISQLDataBase mysql = new MySQL(configFile.getFile(name).getString("hostname"),
                        configFile.getFile(name).getString("username"),
                        configFile.getFile(name).getString("password"),
                        configFile.getFile(name).getString("database"),
                        configFile.getFile(name).getInt("port"));
                mysql.openConnection();
                System.out.println("Successful connected to MySQL");
                break;
            case SQLITE:
                ISQLite sqlite = new SQLite(
                        configFile.getFile(name).getString("hostname"),
                        configFile.getFile(name).getString("username"),
                        configFile.getFile(name).getString("password"),
                        configFile.getFile(name).getString("database"),
                        configFile.getFile(name).getInt("port"));
                File database = sqlite.createDataBase(configFile.getFile(name).getString("database")+".db");
                sqlite.openConnection(database);
                break;
        }
    }

}
