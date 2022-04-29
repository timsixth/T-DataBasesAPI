package pl.timsixth.databasesapi.api;

import pl.timsixth.databasesapi.config.IConfigFile;
import pl.timsixth.databasesapi.database.ISQLDataBase;

public interface IDataBasesApi {

    IConfigFile getConfig();
    ISQLDataBase getCurrentSqlDataBase();
}
