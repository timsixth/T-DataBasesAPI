package pl.timsixth.databasesapi.database.migration;

import java.sql.SQLException;

/**
 * Represents every migration to migrate
 */
public interface IMigration {
    /**
     * @return name of table in database
     */
    String getTableName();

    /**
     * @return migration version
     */
    int getVersion();

    /**
     * This method calls when the migration is migrating
     * @throws SQLException when can not execute query
     */
    void up() throws SQLException;
}
