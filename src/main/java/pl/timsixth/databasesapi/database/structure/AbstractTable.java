package pl.timsixth.databasesapi.database.structure;

import lombok.RequiredArgsConstructor;
import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.exception.TableCreatorException;
import pl.timsixth.databasesapi.database.structure.datatype.IDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * This class is using template method design pattern.
 * See {@link ITable} to more information
 */
@RequiredArgsConstructor
public abstract class AbstractTable implements ITable {

    protected final ISQLDataBase dataBase;
    private final IDataType idDataType;

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

    @Override
    public ITable autoIncrement(String columnName) {
        IColumn column = getColumn(columnName);
        column.setAutoIncrement(true);
        return this;
    }

    @Override
    public ITable primaryKey(String columnName) {
        IColumn column = getColumn(columnName);
        column.setPrimaryKey(true);
        return this;
    }

    @Override
    public ITable createColumn(String columnName, IDataType dataType, boolean nullable) {
        IColumn column = new ColumnImpl(columnName, dataType, nullable);
        columns.add(column);
        return this;
    }

    @Override
    public boolean createTable(String name) {
        if (columns.size() < 2) {
            throw new TableCreatorException("Table must have at least 2 columns");
        }

        String query = String.format("CREATE TABLE IF NOT EXISTS %s(", name);
        StringBuilder stringBuilder = new StringBuilder(query);
        columns.forEach(column -> {
            IDataType type = column.getType();
            stringBuilder.append(column.getName()).append(" ")
                    .append(type.getName());

            if (type.hasLength()) stringBuilder.append(type.getFormattedLength());
            if (!column.isNullable()) stringBuilder.append(" NOT NULL");
            if (column.isPrimaryKey()) stringBuilder.append(" PRIMARY KEY ");
            if (column.isAutoIncrement()) stringBuilder.append(" ").append(getAutoIncrementValue());

            if (column.getDefaultValue() != null) {
                if (type.isNumber()) stringBuilder.append(" DEFAULT ").append(column.getDefaultValue());
                else stringBuilder.append(" DEFAULT '").append(column.getDefaultValue()).append("'");
            }

            if (getColumns().size() > 1) {
                stringBuilder.append(",");
            }
        });
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append(")");

        try {
            dataBase.getAsyncQuery().update(stringBuilder.toString());
            return true;
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Error in query: " + stringBuilder);
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ITable id() {
        createColumn("id", idDataType, false);
        primaryKey("id");
        autoIncrement("id");
        return this;
    }

    protected abstract String getAutoIncrementValue();
}
