package pl.timsixth.databasesapi.tests.migrations;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.migration.ICreationMigration;
import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.type.MySQL;

import java.sql.SQLException;

@RequiredArgsConstructor
public class CreateUsersTableMigration implements ICreationMigration {

    private final MySQL mySQL;

    @Override
    public void up() throws SQLException {
        mySQL.getTableCreator()
                .createColumn("id", DataType.INT, 11, false)
                .primaryKey("id", true)
                .autoIncrement("id", true)
                .createColumn("name", DataType.VARCHAR, 40, true)
                .defaultValue("name", "kokos")
                .create("users_test");
    }

    @Override
    public String getTableName() {
        return "users_test";
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
