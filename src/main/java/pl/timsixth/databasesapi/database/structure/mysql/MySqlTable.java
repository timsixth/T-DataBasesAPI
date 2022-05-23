package pl.timsixth.databasesapi.database.structure.mysql;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.structure.IColumn;
import pl.timsixth.databasesapi.database.structure.ITable;
import pl.timsixth.databasesapi.database.type.MySQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MySqlTable implements ITable {

    private final MySQL mySQL;
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
    public ITable createColumn(String columnName, DataType type, double length, boolean nullable) {
        IColumn column = new MySqlColumn(columnName, type, nullable);
        column.setLength(length);
        columns.add(column);
        return this;
    }

    @Override
    public ITable defaultValue(String columnName, Object value) {
        IColumn column = getColumn(columnName);
        column.setDefaultValue(value);
        return this;
    }

    @Override
    public void create(String name) throws SQLException {
        String queryToCreateTable = "CREATE TABLE IF NOT EXISTS %s(";
        String formattedString = String.format(queryToCreateTable, name);
        StringBuilder stringBuilder = new StringBuilder(formattedString);
        getColumns().forEach(column -> {
            stringBuilder.append(column.getName()).append(" ")
                    .append(column.getDataType().getStringDateType());
            if (column.getDataType() == DataType.INT || column.getDataType() == DataType.VARCHAR) {
                stringBuilder.append("(").append((int) column.getLength())
                        .append(")");
            }
            if (!column.isNullable()) {
                stringBuilder.append(" NOT NULL");
            }
            if (column.isAutoIncrement()) {
                stringBuilder.append(" AUTO_INCREMENT");
            }
            if (column.isPrimaryKey()) {
                stringBuilder.append(" PRIMARY KEY ");
            }
            if (column.getDefaultValue() != null) {
                if (column.getDataType() == DataType.VARCHAR) {
                    stringBuilder.append(" DEFAULT '").append(column.getDefaultValue()).append("'");
                } else {
                    stringBuilder.append(" DEFAULT ").append(column.getDefaultValue());
                }
            }

            if (getColumns().size() > 1) {
                stringBuilder.append(",");
            }
        });
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append(")");
        System.out.println(stringBuilder);
        mySQL.query(stringBuilder.toString()).executeUpdate();
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
