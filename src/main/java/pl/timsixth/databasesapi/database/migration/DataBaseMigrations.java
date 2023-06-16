package pl.timsixth.databasesapi.database.migration;

import lombok.Getter;
import pl.timsixth.databasesapi.DatabasesApiPlugin;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.type.SQLite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class DataBaseMigrations {
    protected final String MIGRATION_TABLE_NAME = "dataBase_migrations";

    @Getter
    private final List<DataBaseMigration> dataBaseMigrations = new ArrayList<>();

    /**
     * Creates dataBaseMigrations table
     */
    public void createMigrationsTable() throws SQLException {
        ISQLDataBase currentSqlDataBase = DatabasesApiPlugin.getApi().getCurrentSqlDataBase();

        if (currentSqlDataBase instanceof SQLite) {
            createMigrationsTable(currentSqlDataBase, DataType.INTEGER);
        } else {
            createMigrationsTable(currentSqlDataBase, DataType.INT);
        }
    }


    protected void createMigrationsTable(ISQLDataBase dataBase, DataType dataType) throws SQLException {
        dataBase.getTableCreator()
                .createColumn("id", dataType, 11, false)
                .createColumn("migrated_table", DataType.VARCHAR, 30, false)
                .createColumn("version", DataType.INT, 11, false)
                .autoIncrement("id", true)
                .primaryKey("id", true)
                .create(MIGRATION_TABLE_NAME);
    }

    public Optional<DataBaseMigration> getMigration(String tableName) {
        return dataBaseMigrations.stream()
                .filter(dataBaseMigration -> dataBaseMigration.getTableName().equalsIgnoreCase(tableName))
                .findAny();
    }

    /**
     * Clears all migrations
     */
    public void unload() {
        dataBaseMigrations.clear();
    }

    /**
     * Gets all dataBaseMigrations versions
     *
     * @throws SQLException when can not execute query
     */
    public void checkMigrations() throws SQLException {
        ISQLDataBase currentSqlDataBase = DatabasesApiPlugin.getApi().getCurrentSqlDataBase();

        ResultSet resultSet = currentSqlDataBase.query("SELECT * FROM " + MIGRATION_TABLE_NAME).executeQuery();

        while (resultSet.next()) {
            DataBaseMigration dataBaseMigration = new DataBaseMigration(
                    resultSet.getString("migrated_table"),
                    resultSet.getInt("version")
            );

            dataBaseMigrations.add(dataBaseMigration);
        }
    }

    /**
     * Adds new record with information about migration
     *
     * @param migration the new migration
     * @throws SQLException when can not execute query
     */
    public void addNewMigration(IMigration migration) throws SQLException {
        ISQLDataBase currentSqlDataBase = DatabasesApiPlugin.getApi().getCurrentSqlDataBase();

        String query = "INSERT INTO " + MIGRATION_TABLE_NAME + " VALUES(NULL,'%s',%d)";

        currentSqlDataBase.query(String.format(query, migration.getTableName(), migration.getVersion())).executeUpdate();

        dataBaseMigrations.add(new DataBaseMigration(migration.getTableName(), migration.getVersion()));
    }

    /**
     * Updates the dataBaseMigrations table when it is not up-to-date
     *
     * @param migration the migration
     * @throws SQLException when can not execute query
     */
    public void updateMigration(DataBaseMigration dataBaseMigration, IMigration migration) throws SQLException {
        ISQLDataBase currentSqlDataBase = DatabasesApiPlugin.getApi().getCurrentSqlDataBase();

        String sql = "UPDATE " + MIGRATION_TABLE_NAME + " SET version = " + migration.getVersion() + " WHERE migrated_table = '" + migration.getTableName() + "'";

        currentSqlDataBase.query(sql).executeUpdate();

        dataBaseMigration.setCurrentVersion(migration.getVersion());
    }

    /**
     * Rollbacks all migrations from database
     */
    public void rollbackMigrations() throws ExecutionException, InterruptedException {
        ISQLDataBase currentSqlDataBase = DatabasesApiPlugin.getApi().getCurrentSqlDataBase();

        String truncateMigrationTable = "TRUNCATE " + MIGRATION_TABLE_NAME;

        try {
            currentSqlDataBase.getAsyncQuery().update(truncateMigrationTable);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (DataBaseMigration dataBaseMigration : dataBaseMigrations) {
            currentSqlDataBase.getAsyncQuery().update("DROP " + dataBaseMigration.getTableName());
        }

        dataBaseMigrations.clear();
    }
}
