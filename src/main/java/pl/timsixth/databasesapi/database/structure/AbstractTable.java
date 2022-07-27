package pl.timsixth.databasesapi.database.structure;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractTable implements ITable {

    private final List<IColumn> columns = new ArrayList<>();

    @Override
    public ITable autoIncrement(String columnName, boolean autoIncrement) {
        IColumn column = getColumn(columnName);
        column.setAutoIncrement(autoIncrement);
        return this;
    }

    @Override
    public ITable primaryKey(String columnName, boolean primaryKey) {
        IColumn column = getColumn(columnName);
        column.setPrimaryKey(primaryKey);
        return this;
    }

    @Override
    public ITable defaultValue(String columnName, Object value) {
        IColumn column = getColumn(columnName);
        column.setDefaultValue(value);
        return this;
    }

    @Override
    public List<IColumn> getColumns() {
        return columns;
    }

    @Override
    public IColumn getColumn(String name) {
        return columns.stream().filter(column -> column.getName().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }
}
