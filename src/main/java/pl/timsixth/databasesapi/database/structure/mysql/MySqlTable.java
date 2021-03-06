package pl.timsixth.databasesapi.database.structure.mysql;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.exception.TableCreatorException;
import pl.timsixth.databasesapi.database.structure.AbstractTable;
import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.structure.IColumn;
import pl.timsixth.databasesapi.database.structure.ITable;
import pl.timsixth.databasesapi.database.type.MySQL;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MySqlTable extends AbstractTable{

    private final MySQL mySQL;

    @Override
    public ITable createColumn(String columnName, DataType type, double length, boolean nullable) {
        IColumn column = new MySqlColumn(columnName,type, nullable);
        column.setLength(length);
        getColumns().add(column);
        return this;
    }

    @Override
    public void create(String name) throws SQLException {
        if (getColumns().size() < 2){
            throw new TableCreatorException("Table must have at least 2 columns");
        }

        String queryToCreateTable = "CREATE TABLE IF NOT EXISTS %s(";
        String formattedString = String.format(queryToCreateTable, name);
        StringBuilder stringBuilder = new StringBuilder(formattedString);
        getColumns().forEach(column -> {
            stringBuilder.append(column.getName()).append(" ")
                    .append(column.getDataType().getTextNameDateType());
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
        mySQL.query(stringBuilder.toString()).executeUpdate();
    }
}
