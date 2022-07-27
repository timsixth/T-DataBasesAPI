package pl.timsixth.databasesapi.database.structure.sqlite;

import lombok.ToString;
import pl.timsixth.databasesapi.database.structure.AbstractColumn;
import pl.timsixth.databasesapi.database.structure.DataType;

@ToString
public class SqliteColumn extends AbstractColumn {

    public SqliteColumn(String name, DataType dataType, boolean isNullable) {
        super(name, dataType, isNullable);
    }
}
