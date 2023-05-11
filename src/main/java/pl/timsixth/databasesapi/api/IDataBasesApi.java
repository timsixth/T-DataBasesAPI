package pl.timsixth.databasesapi.api;

import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.migration.Migrations;

/**
 * The interface to manage API
 */
public interface IDataBasesApi {

    IConfigFile getConfig();
    ISQLDataBase getCurrentSqlDataBase();

    Migrations getMigrations();

}
