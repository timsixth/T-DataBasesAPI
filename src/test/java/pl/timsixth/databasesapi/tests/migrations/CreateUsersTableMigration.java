package pl.timsixth.databasesapi.tests.migrations;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.migration.ICreationMigration;
import pl.timsixth.databasesapi.database.structure.datatype.VarcharDataType;
import pl.timsixth.databasesapi.database.type.MySQL;

@RequiredArgsConstructor
public class CreateUsersTableMigration implements ICreationMigration {

    private final MySQL mySQL;

    @Override
    public void up() {
        mySQL.getTableCreator()
                .id()
                .createColumn("name", new VarcharDataType(40), true)
                .defaultValue("name", "kokos")
                .createTable("users_test");
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
