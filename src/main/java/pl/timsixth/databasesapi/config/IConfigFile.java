package pl.timsixth.databasesapi.config;

import pl.timsixth.databasesapi.database.DataBaseType;

/**
 * Represents config.yml
 */
public interface IConfigFile {
    /**
     * Gets database type from config.yml
     *
     * @return database type
     */
    DataBaseType getDataBaseType();


    /**
     * @param name database type from config
     * @return database type
     * @deprecated
     */
    @Deprecated
    default DataBaseType getDataBaseType(String name) {
        return DataBaseType.valueOf(name.toUpperCase());
    }

}
