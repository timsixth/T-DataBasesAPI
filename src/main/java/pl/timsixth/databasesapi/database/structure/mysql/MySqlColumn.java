package pl.timsixth.databasesapi.database.structure.mysql;

import lombok.ToString;
import pl.timsixth.databasesapi.database.structure.AbstractColumn;
import pl.timsixth.databasesapi.database.structure.DataType;
@ToString
public class MySqlColumn extends AbstractColumn {

    public MySqlColumn(String name, DataType dataType, boolean isNullable) {
        super(name, dataType, isNullable);
    }
}
