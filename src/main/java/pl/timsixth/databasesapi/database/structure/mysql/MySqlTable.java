package pl.timsixth.databasesapi.database.structure.mysql;

import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.structure.AbstractTable;
import pl.timsixth.databasesapi.database.structure.ITable;
import pl.timsixth.databasesapi.database.structure.datatype.DataTypes;

/**
 * Represents mysql table
 * See {@link ITable} to more information
 */
public class MySqlTable extends AbstractTable {

    public MySqlTable(ISQLDataBase dataBase) {
        super(dataBase, DataTypes.INT);
    }

    @Override
    protected String getAutoIncrementValue() {
        return "AUTO_INCREMENT";
    }
}
