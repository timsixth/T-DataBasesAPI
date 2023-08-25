package pl.timsixth.databasesapi.database.structure;

import lombok.Getter;
import lombok.Setter;
import pl.timsixth.databasesapi.database.structure.datatype.IDataType;

/**
 * This class is using template method design pattern.
 * See {@link IColumn} to more information
 */
@Getter
@Setter
public abstract class AbstractColumn implements IColumn{

    private final String name;
    @Deprecated
    private DataType dataType;
    private IDataType type;
    private final boolean isNullable;
    private double length;
    private boolean primaryKey;
    private boolean autoIncrement;
    private Object defaultValue;

    public AbstractColumn(String name, IDataType type, boolean isNullable) {
        this.name = name;
        this.isNullable = isNullable;
        this.type = type;
    }
    @Deprecated
    public AbstractColumn(String name, DataType dataType, boolean isNullable) {
        this.name = name;
        this.dataType = dataType;
        this.isNullable = isNullable;
    }
}
