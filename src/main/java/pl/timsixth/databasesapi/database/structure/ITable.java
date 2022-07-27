package pl.timsixth.databasesapi.database.structure;

import java.sql.SQLException;
import java.util.List;

public interface ITable {

    ITable autoIncrement(String columnName,boolean autoIncrement);
    ITable primaryKey(String columnName,boolean primaryKey);
    ITable createColumn(String columnName, DataType type, double length, boolean nullable);

    ITable defaultValue(String columnName, Object value);
    void create(String name) throws SQLException;

    List<IColumn> getColumns();

    IColumn getColumn(String name);
}
