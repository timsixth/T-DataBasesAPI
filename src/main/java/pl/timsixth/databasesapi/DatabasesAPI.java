package pl.timsixth.databasesapi;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.api.IDataBasesApi;
import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.migration.Migrations;

@RequiredArgsConstructor
public final class DatabasesAPI implements IDataBasesApi {

    private final DatabasesApiPlugin databasesApiPlugin;

    @Override
    public IConfigFile getConfig() {
        return databasesApiPlugin.configFile;
    }

    @Override
    public ISQLDataBase getCurrentSqlDataBase() {
        return databasesApiPlugin.currentSQLDataBase;
    }

    @Override
    public Migrations getMigrations() {
        return databasesApiPlugin.migrations;
    }

}
