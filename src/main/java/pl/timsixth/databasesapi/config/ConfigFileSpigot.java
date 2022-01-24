package pl.timsixth.databasesapi.config;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.DataBaseType;
import pl.timsixth.databasesapi.spigot.DatabasesAPISpigot;

@RequiredArgsConstructor
public class ConfigFileSpigot implements IConfigFile {

    private final DatabasesAPISpigot databasesAPISpigot;

    @Override
    public DataBaseType getDataBaseType() {
        return DataBaseType.valueOf(databasesAPISpigot.getConfig().getString("type").toUpperCase());
    }
}
