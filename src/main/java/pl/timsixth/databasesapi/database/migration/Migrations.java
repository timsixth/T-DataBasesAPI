package pl.timsixth.databasesapi.database.migration;

import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class manages every migration to migrate
 */
@AllArgsConstructor
public final class Migrations {

    private final DataBaseMigrations dataBaseMigrations;
    private final List<IMigration> migrations = new ArrayList<>();

    /**
     * Adds new migration
     *
     * @param migration the creation migration
     */
    public void addMigration(IMigration migration) {
        migrations.add(migration);
    }

    /**
     * Adds list of migration to migrations
     *
     * @param migrations list of migration
     */
    public void addMigrations(List<IMigration> migrations) {
        this.migrations.addAll(migrations);
    }

    /**
     * Removes the migration
     *
     * @param migration the creation migration
     */
    public void removeMigration(IMigration migration) {
        migrations.remove(migration);
    }

    /**
     * Migrate all migrations
     */
    public void migrateAll() {
        for (IMigration migration : migrations) {
            Optional<DataBaseMigration> migrationOptional = dataBaseMigrations.getMigration(migration.getTableName());
            if (!migrationOptional.isPresent()) {
                if (migration instanceof ICreationMigration) {
                    migrateCreateMigration(migration);
                    continue;
                }
                return;
            }

            DataBaseMigration migrationFromDataBase = migrationOptional.get();
            migrateEditMigration(migration, migrationFromDataBase);
        }
    }

    /**
     * Migrates only creation migration
     *
     * @param migration migration to migrate
     */
    private void migrateCreateMigration(IMigration migration) {
        try {
            migration.up();
            dataBaseMigrations.addNewMigration(migration);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Migrates only edition migration
     *
     * @param migration             migration to migrate
     * @param migrationFromDataBase migration in database
     */
    private void migrateEditMigration(IMigration migration, DataBaseMigration migrationFromDataBase) {
        if (migrationFromDataBase.getCurrentVersion() < migration.getVersion()) {
            if (migration instanceof IEditionMigration) {
                try {
                    migration.up();
                    dataBaseMigrations.updateMigration(migrationFromDataBase, migration);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Clears list with migrations
     */
    public void unload() {
        migrations.clear();
    }

    /**
     * Migrates single migration to database
     *
     * @param migration migration to migrate
     */
    public void migrate(IMigration migration) {
        Optional<DataBaseMigration> migrationOptional = dataBaseMigrations.getMigration(migration.getTableName());

        if (!migrationOptional.isPresent()) {
            if (migration instanceof ICreationMigration) {
                migrateCreateMigration(migration);
            }
        } else {
            migrateEditMigration(migration, migrationOptional.get());
        }
    }

}
