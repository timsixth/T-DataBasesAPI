package pl.timsixth.databasesapi.database.structure.datatype;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.timsixth.databasesapi.database.structure.datatype.sqlite.IntegerDataType;

/**
 * Template method of {@link IDataType}
 */
@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractDataType implements IDataType {

    private final String name;
    private double length = -1;

    public AbstractDataType(@NonNull String name, @NonNull double length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public boolean hasLength() {
        return length != -1;
    }

    @Override
    public String getFormattedLength() {
        return "(" + (int) length + ")";
    }

    @Override
    public boolean isNumber() {
        return this instanceof IntegerDataType
                || this instanceof IntDataType
                || this instanceof BooleanDataType
                || this instanceof DoubleDataType;
    }
}
