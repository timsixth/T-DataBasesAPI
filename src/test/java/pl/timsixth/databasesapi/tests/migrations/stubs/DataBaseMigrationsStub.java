package pl.timsixth.databasesapi.tests.migrations.stubs;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.migration.DataBaseMigration;
import pl.timsixth.databasesapi.database.migration.DataBaseMigrations;
import pl.timsixth.databasesapi.database.migration.IMigration;
import pl.timsixth.databasesapi.database.structure.DataType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class DataBaseMigrationsStub extends DataBaseMigrations {

    private final ISQLDataBase mySQL;

    @Override
    public void createMigrationsTable() throws SQLException {
        createMigrationsTable(mySQL, DataType.INT);
    }

    @Override
    public void checkMigrations() throws SQLException {
        ResultSet resultSet = mySQL.query("SELECT * FROM " + MIGRATION_TABLE_NAME).executeQuery();

        while (resultSet.next()) {
            DataBaseMigration dataBaseMigration = new DataBaseMigration(
                    resultSet.getString("migrated_table"),
                    resultSet.getInt("version")
            );

            getDataBaseMigrations().add(dataBaseMigration);
        }
    }

    @Override
    public void addNewMigration(IMigration migration) throws SQLException {
        String query = "INSERT INTO " + MIGRATION_TABLE_NAME + " VALUES(NULL,'%s',%d)";

        mySQL.query(String.format(query, migration.getTableName(), migration.getVersion())).executeUpdate();

        getDataBaseMigrations().add(new DataBaseMigration(migration.getTableName(), migration.getVersion()));
    }

    @Override
    public void updateMigration(DataBaseMigration dataBaseMigration, IMigration migration) throws SQLException {
        String sql = "UPDATE " + MIGRATION_TABLE_NAME + " SET version = " + migration.getVersion() + " WHERE migrated_table = '" + migration.getTableName() + "'";

        mySQL.query(sql).executeUpdate();

        dataBaseMigration.setCurrentVersion(migration.getVersion());
    }

    @Override
    public void rollbackMigrations() throws ExecutionException, InterruptedException {
        String truncateMigrationTable = "TRUNCATE " + MIGRATION_TABLE_NAME;

        try {
            mySQL.getAsyncQuery().update(truncateMigrationTable);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (DataBaseMigration dataBaseMigration : getDataBaseMigrations()) {
            mySQL.getAsyncQuery().update("DROP TABLE " + dataBaseMigration.getTableName());
        }

        getDataBaseMigrations().clear();
    }
}
