package pl.timsixth.databasesapi.database.structure.datatype;

import pl.timsixth.databasesapi.database.structure.datatype.sqlite.IntegerDataType;

public final class SQLiteDataTypes extends DataTypes {

    private SQLiteDataTypes() {
    }

    public static final IDataType INTEGER = new IntegerDataType();
}
