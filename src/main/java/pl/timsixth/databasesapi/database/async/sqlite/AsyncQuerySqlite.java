package pl.timsixth.databasesapi.database.async.sqlite;

import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.async.AbstractAsyncQuery;

public class AsyncQuerySqlite extends AbstractAsyncQuery<ISQLite> {

    public AsyncQuerySqlite(ISQLite database) {
        super(database);
    }
}
