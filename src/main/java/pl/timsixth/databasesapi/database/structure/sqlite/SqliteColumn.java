package pl.timsixth.databasesapi.database.structure.sqlite;

import pl.timsixth.databasesapi.database.structure.DataType;
import pl.timsixth.databasesapi.database.structure.IColumn;

public class SqliteColumn implements IColumn {




    @Override
    public String getName() {
        return null;
    }

    @Override
    public DataType getDataType() {
        return null;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public double getLength() {
        return 0;
    }

    @Override
    public void setLength(double length) {

    }

    @Override
    public boolean isPrimaryKey() {
        return false;
    }

    @Override
    public void setPrimaryKey(boolean primaryKey) {

    }

    @Override
    public boolean isAutoIncrement() {
        return false;
    }

    @Override
    public void setAutoIncrement(boolean autoIncrement) {

    }

    @Override
    public Object getDefaultValue() {
        return null;
    }

    @Override
    public void setDefaultValue(Object object) {

    }
}
