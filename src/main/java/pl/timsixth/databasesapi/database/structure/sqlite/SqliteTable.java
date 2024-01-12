package pl.timsixth.databasesapi.database.structure.sqlite;

import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.structure.AbstractTable;
import pl.timsixth.databasesapi.database.structure.ITable;
import pl.timsixth.databasesapi.database.structure.datatype.DataTypes;

/**
 * Represents sqlite table
 * See {@link ITable} to more information
 */
public class SqliteTable extends AbstractTable {

    public SqliteTable(ISQLDataBase dataBase) {
        super(dataBase, DataTypes.INTEGER);
    }

    @Override
    protected String getAutoIncrementValue() {
        return "AUTOINCREMENT";
    }
}
