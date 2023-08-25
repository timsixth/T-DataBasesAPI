package pl.timsixth.databasesapi.database.structure.datatype;

public final class DoubleDataType extends AbstractDataType {

    public DoubleDataType(Double length) {
        super("double", length);
    }

    @Override
    public String getFormattedLength() {
        return "(" + String.valueOf(getLength()).replace(".", ",") + ")";
    }
}
