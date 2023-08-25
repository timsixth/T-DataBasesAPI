package pl.timsixth.databasesapi.database.structure.datatype;

public final class VarcharDataType extends AbstractDataType{

    public VarcharDataType(int length) {
        super("varchar", length);
    }
}
