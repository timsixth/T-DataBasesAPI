package pl.timsixth.databasesapi.database.structure.sqlite;

import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.structure.IColumn;
import pl.timsixth.databasesapi.database.structure.ITable;

import java.sql.SQLException;
import java.util.List;

public class SqliteTable implements ITable {

    @Override
    public ITable autoIncrement(String columnName, boolean autoIncrement) {
        return null;
    }

    @Override
    public ITable primaryKey(String columnName, boolean primaryKey) {
        return null;
    }

    @Override
    public ITable createColumn(String columnName, DataType type, double length, boolean nullable) {
        return null;
    }

    @Override
    public ITable defaultValue(String columnName, Object value) {
        return null;
    }

    @Override
    public void create(String name) throws SQLException {

    }

    @Override
    public List<IColumn> getColumns() {
        return null;
    }

    @Override
    public IColumn getColumn(String name) {
        return null;
    }
}
