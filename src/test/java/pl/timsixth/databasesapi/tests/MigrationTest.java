package pl.timsixth.databasesapi.tests;

import org.junit.Before;
import org.junit.Test;
import pl.timsixth.databasesapi.database.migration.Migrations;
import pl.timsixth.databasesapi.database.type.MySQL;
import pl.timsixth.databasesapi.tests.migrations.AlterUsersTableMigration;
import pl.timsixth.databasesapi.tests.migrations.CreateUsersTableMigration;
import pl.timsixth.databasesapi.tests.migrations.stubs.DataBaseMigrationsStub;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertEquals;

public class MigrationTest {

    private Migrations migrations;
    private CreateUsersTableMigration createUsersTableMigration;
    private DataBaseMigrationsStub dataBaseMigrationsStub;

    @Before
    public void init() throws SQLException {
        MySQL mysql = new MySQL("localhost", "root", "", "servertestowy", 3306);
        mysql.openConnection();

        dataBaseMigrationsStub = new DataBaseMigrationsStub(mysql);

        migrations = new Migrations(dataBaseMigrationsStub);

        dataBaseMigrationsStub.createMigrationsTable();
        dataBaseMigrationsStub.checkMigrations();

        createUsersTableMigration = new CreateUsersTableMigration(mysql);

        migrations.addMigrations(Arrays.asList(
                createUsersTableMigration,
                new AlterUsersTableMigration(mysql)
        ));
    }

    @Test
    public void shouldMigrateAll() {
        migrations.migrateAll();

        assertEquals(1, dataBaseMigrationsStub.getDataBaseMigrations().size());
    }

    @Test
    public void shouldMigrateSingleMigration() {
        migrations.migrate(createUsersTableMigration);

        assertEquals(1, dataBaseMigrationsStub.getDataBaseMigrations().size());
    }

    @Test
    public void shouldRollbackAllMigrations() throws ExecutionException, InterruptedException {
        dataBaseMigrationsStub.rollbackMigrations();

        assertEquals(0, dataBaseMigrationsStub.getDataBaseMigrations().size());
    }
}
