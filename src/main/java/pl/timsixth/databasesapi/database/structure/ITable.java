package pl.timsixth.databasesapi.database.structure;

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
     */
    ITable autoIncrement(String columnName, boolean autoIncrement);

    /**
     * Sets primaryKey to column
     *
     * @param columnName column which will be set primaryKey
     * @param primaryKey if true will be set primaryKey otherwise will not set primaryKey
     * @return ITable
     */
    ITable primaryKey(String columnName, boolean primaryKey);

    /**
     * Creates new column
     *
     * @param columnName column which will be set primaryKey
     * @param type       DataType which will be set to column @{@link DataType}
     * @param length     of column
     * @param nullable   is nullable or not
     * @return ITable
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
     * Creates with sync query new table with name
     *
     * @param name of table
     * @throws SQLException when can not create table
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
}
