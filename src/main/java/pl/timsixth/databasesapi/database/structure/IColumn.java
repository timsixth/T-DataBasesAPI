package pl.timsixth.databasesapi.database.structure;

import pl.timsixth.databasesapi.database.structure.datatype.IDataType;

/**
 * Represents every SQL database column
 */
public interface IColumn {
    /**
     * Gets colum's name
     *
     * @return column name
     */
    String getName();

    /**
     * @return true is nullable, otherwise null
     */
    boolean isNullable();

    /**
     * @return true when column has primary key, otherwise null
     */
    boolean isPrimaryKey();

    /**
     * Sets primary key to true or false
     *
     * @param value true to set primary key
     */
    void setPrimaryKey(boolean value);

    /**
     * @return true when column has auto increment, otherwise null
     */
    boolean isAutoIncrement();

    /**
     * Sets auto increment to true or false
     *
     * @param value true to set auto increment
     */
    void setAutoIncrement(boolean value);

    /**
     * Gets default value of column
     *
     * @return default value
     */
    Object getDefaultValue();

    /**
     * Sets default value
     *
     * @param object value to set
     */
    void setDefaultValue(Object object);

    /**
     * Gets data type of column
     *
     * @return data type
     */
    IDataType getType();
}
