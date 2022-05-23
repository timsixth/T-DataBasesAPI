package pl.timsixth.databasesapi.database.async.mysql;

import pl.timsixth.databasesapi.database.ISQLDataBase;
import pl.timsixth.databasesapi.database.async.AbstractAsyncQuery;

public class AsyncQueryMySQL extends AbstractAsyncQuery<ISQLDataBase> {

    public AsyncQueryMySQL(ISQLDataBase database) {
        super(database);
    }
}
