package pl.timsixth.databasesapi.api;

import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.migration.DataBaseMigrations;
import pl.timsixth.databasesapi.database.migration.Migrations;

/**
 * The interface to manage API
 */
public interface IDataBasesApi {
    /**
     * @return interface which represents config file
     */
    IConfigFile getConfig();

    /**
     * @return current sql database which is connected
     */
    ISQLDataBase getCurrentSqlDataBase();

    /**
     * @return the class which represents every migration to migrate
     */
    Migrations getMigrations();

    /**
     * @return the class which represents every migration in database
     */
    DataBaseMigrations getDataBaseMigrations();
}
