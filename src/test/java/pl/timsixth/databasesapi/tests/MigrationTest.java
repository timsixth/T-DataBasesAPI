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

public class MigrationTest {

    private Migrations migrations;

    @Before
    public void init() throws SQLException {
        MySQL mysql = new MySQL("localhost", "root", "", "servertestowy", 3306);
        mysql.openConnection();

        DataBaseMigrationsStub dataBaseMigrationsStub = new DataBaseMigrationsStub(mysql);

        migrations = new Migrations(dataBaseMigrationsStub);

        dataBaseMigrationsStub.createMigrationsTable();
        dataBaseMigrationsStub.checkMigrations();

        migrations.addMigrations(Arrays.asList(
                new CreateUsersTableMigration(mysql),
                new AlterUsersTableMigration(mysql)
        ));
    }

    @Test
    public void shouldMigrateAll() {
        migrations.migrateAll();
    }
}
