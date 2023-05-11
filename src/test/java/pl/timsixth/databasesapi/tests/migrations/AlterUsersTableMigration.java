package pl.timsixth.databasesapi.tests.migrations;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.migration.IEditionMigration;
import pl.timsixth.databasesapi.database.type.MySQL;

import java.sql.SQLException;

@RequiredArgsConstructor
public class AlterUsersTableMigration implements IEditionMigration {

    private final MySQL mySQL;

    @Override
    public void up() throws SQLException {
        mySQL.query("ALTER TABLE users_test ADD COLUMN password varchar(100) not null").executeUpdate();
    }

    @Override
    public String getTableName() {
        return "users_test";
    }

    @Override
    public int getVersion() {
        return 2;
    }
}
