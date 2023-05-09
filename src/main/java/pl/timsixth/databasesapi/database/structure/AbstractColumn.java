package pl.timsixth.databasesapi.database.structure;

import lombok.RequiredArgsConstructor;
/**
 * This class is using template method design pattern.
 * See {@link IColumn} to more information
 */
@RequiredArgsConstructor
public abstract class AbstractColumn implements IColumn{

    private final String name;
    private final DataType dataType;
    private final boolean isNullable;
    private double length;
    private boolean primaryKey;
    private boolean autoIncrement;
    private Object defaultValue;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public boolean isNullable() {
        return isNullable;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    @Override
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    @Override
    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(Object object) {
        this.defaultValue = object;
    }

}
