package pl.timsixth.databasesapi.database.structure;

import pl.timsixth.databasesapi.database.structure.datatype.IDataType;

/**
 * Represents column in SQL database
 */
final class ColumnImpl extends AbstractColumn {
    public ColumnImpl(String name, IDataType type, boolean isNullable) {
        super(name, type, isNullable);
    }
}
