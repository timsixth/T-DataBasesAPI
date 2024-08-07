package pl.timsixth.databasesapi.database.structure.datatype;

public final class MySQLDataTypes extends DataTypes {

    private MySQLDataTypes() {

    }

    public static final IDataType JSON = new JSONDataType();
}
