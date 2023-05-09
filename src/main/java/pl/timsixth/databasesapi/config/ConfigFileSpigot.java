package pl.timsixth.databasesapi.config;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.DataBaseType;
import pl.timsixth.databasesapi.DatabasesApiPlugin;

/**
 * @see IConfigFile
 */
@RequiredArgsConstructor
public class ConfigFileSpigot implements IConfigFile {

    private final DatabasesApiPlugin databasesApiPlugin;

    @Override
    public DataBaseType getDataBaseType() {
        return DataBaseType.valueOf(databasesApiPlugin.getConfig().getString("type").toUpperCase());
    }
}
