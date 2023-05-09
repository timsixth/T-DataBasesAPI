package pl.timsixth.databasesapi.database.structure;

/**
 * Represents every SQL database column
 */
public interface IColumn {

    String getName();

    /**
     * {@link DataType}
     *
     * @return data type of column
     */
    DataType getDataType();

    boolean isNullable();

    /**
     * @return length in double. For types varchar and int will be cast to int
     */
    double getLength();

    void setLength(double length);

    boolean isPrimaryKey();

    void setPrimaryKey(boolean primaryKey);

    boolean isAutoIncrement();

    void setAutoIncrement(boolean autoIncrement);

    Object getDefaultValue();

    void setDefaultValue(Object object);

}
