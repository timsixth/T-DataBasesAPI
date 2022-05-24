package pl.timsixth.databasesapi.database.structure;

public interface IColumn {

    String getName();

    DataType getDataType();

    boolean isNullable();

    double getLength();

    void setLength(double length);

    boolean isPrimaryKey();

    void setPrimaryKey(boolean primaryKey);

    boolean isAutoIncrement();

    void setAutoIncrement(boolean autoIncrement);

    Object getDefaultValue();

    void setDefaultValue(Object object);

}
