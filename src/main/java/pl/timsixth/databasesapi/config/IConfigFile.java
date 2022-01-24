package pl.timsixth.databasesapi.config;

import pl.timsixth.databasesapi.database.DataBaseType;

import java.io.IOException;

public interface IConfigFile {

    DataBaseType getDataBaseType() throws IOException;
    default DataBaseType getDataBaseType(String name) {
        return DataBaseType.valueOf(name.toUpperCase());
    }

}
