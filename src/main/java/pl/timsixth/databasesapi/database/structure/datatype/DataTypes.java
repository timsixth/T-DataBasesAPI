package pl.timsixth.databasesapi.database.structure.datatype;

import pl.timsixth.databasesapi.database.structure.datatype.sqlite.IntegerDataType;

public final class DataTypes {

    private DataTypes() {
    }

    public static final IDataType DATE = new DateDataType();
    public static final IDataType INT = new IntDataType(11);
    public static final IDataType VARCHAR = new VarcharDataType(255);
    public static final IDataType INTEGER = new IntegerDataType();
    public static final IDataType DOUBLE = new DoubleDataType(5.2);
    public static final IDataType DATETIME = new DatetimeDataType();
    public static final IDataType BOOLEAN = new BooleanDataType();
}
