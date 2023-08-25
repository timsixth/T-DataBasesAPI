package pl.timsixth.databasesapi.database.structure;

import pl.timsixth.databasesapi.database.structure.datatype.IDataType;

import java.sql.SQLException;
import java.util.List;

/**
 * Represents every SQL table
 */
public interface ITable {

    /**
     * Sets autoIncrement to column
     *
     * @param columnName    column which will be set autoIncrement
     * @param autoIncrement if true will be set autoIncrement otherwise will not set autoIncrement
     * @return ITable
     * @deprecated
     */
    @Deprecated
    ITable autoIncrement(String columnName, boolean autoIncrement);

    /**
     * Sets primaryKey to column
     *
     * @param columnName column which will be set primaryKey
     * @param primaryKey if true will be set primaryKey otherwise will not set primaryKey
     * @return ITable
     * @deprecated
     */
    @Deprecated
    ITable primaryKey(String columnName, boolean primaryKey);

    /**
     * Creates new column
     *
     * @param columnName column which will be set primaryKey
     * @param type       DataType which will be set to column @{@link DataType}
     * @param length     of column
     * @param nullable   is nullable or not
     * @return ITable
     * @deprecated
     */
    ITable createColumn(String columnName, DataType type, double length, boolean nullable);

    /**
     * Sets default value to column
     *
     * @param columnName column which will be set defaultValue
     * @param value      to set
     * @return ITable
     */
    ITable defaultValue(String columnName, Object value);

    /**
     * Creates with async query new table with name
     *
     * @param name of table
     * @throws SQLException when can not create table
     * @deprecated use {@link #createTable(String)}
     */
    void create(String name) throws SQLException;

    /**
     * @return list of all columns
     */
    List<IColumn> getColumns();

    /**
     * Returns column {@link IColumn}
     *
     * @param name of column
     * @return column
     */
    IColumn getColumn(String name);

    /**
     * Sets autoIncrement to column
     *
     * @param columnName column which will be set autoIncrement
     * @return ITable
     */
    ITable autoIncrement(String columnName);

    /**
     * Sets primaryKey to column
     *
     * @param columnName column which will be set primaryKey
     * @return ITable
     */
    ITable primaryKey(String columnName);

    /**
     * Creates new column
     *
     * @param columnName column which will be set primaryKey
     * @param type       DataType which will be set to column @{@link IDataType}
     * @param nullable   is nullable or not
     * @return ITable
     */
    ITable createColumn(String columnName, IDataType type, boolean nullable);

    /**
     * Creates with async query new table with name
     *
     * @param name of table
     * @return true when table created otherwise false
     */
    boolean createTable(String name);

    /**
     * Creates id in table
     *
     * @return ITable
     */
    ITable id();
}
