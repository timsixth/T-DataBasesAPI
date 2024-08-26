package pl.timsixth.databasesapi.database.query;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * Creates queries
 */
public final class QueryBuilder {
    private final StringBuilder query = new StringBuilder();

    /**
     * Creates new instance of query builder
     *
     * @return QueryBuilder
     */
    public static QueryBuilder createQueryBuilder() {
        return new QueryBuilder();
    }

    /**
     * @param table        the table name
     * @param columnsNames columns to queried
     * @return QueryBuilder
     */
    public QueryBuilder select(String table, String... columnsNames) {
        query.append("SELECT ");

        setColumns(columnsNames);

        from(table);

        return this;
    }

    /**
     * Creates select query with all columns
     *
     * @param table the table name
     * @return QueryBuilder
     */
    public QueryBuilder selectAll(String table) {
        return select(table, "*");
    }

    /**
     * Creates the delete query
     *
     * @param table        the table name
     * @param columnsNames columns to queried
     * @return QueryBuilder
     */
    public QueryBuilder delete(String table, String... columnsNames) {
        if (columnsNames.length != 0) {
            query.append("DELETE ");
            setColumns(columnsNames);
        } else {
            query.append("DELETE");
        }

        from(table);

        return this;
    }

    /**
     * Creates delete query with all columns
     *
     * @param table the table name
     * @return QueryBuilder
     */
    public QueryBuilder deleteAll(String table) {
        return delete(table);
    }

    /**
     * Adds where condition to query
     *
     * @param whereCondition condition
     * @return QueryBuilder
     */
    public QueryBuilder where(String whereCondition) {
        query.append(" WHERE ");

        query.append(whereCondition);

        return this;
    }

    /**
     * Creates inesrt into query
     *
     * @param table the table name
     * @param data  the data to insert
     * @return QueryBuilder
     */
    public QueryBuilder insert(String table, Object... data) {
        if (data.length == 0) throw new IllegalStateException("data can not be empty");

        query.append("INSERT INTO ").append(table);

        query.append(" VALUES(");

        for (Object datum : data) {
            if (datum instanceof Collection) {
                Collection<?> collection = (Collection<?>) datum;

                for (Object object : collection) {
                    inesrtValue(object);
                }
            } else {
                inesrtValue(datum);
            }
        }

        query.deleteCharAt(query.lastIndexOf(","));

        query.append(")");

        return this;
    }

    private void inesrtValue(Object datum) {
        if (datum instanceof String || datum instanceof UUID)
            query.append("'").append(datum).append("'").append(",");
        else query.append(datum).append(",");
    }

    /**
     * Creates update query
     *
     * @param table the table name
     * @param data  the data to update
     * @return QueryBuilder
     */
    public QueryBuilder update(String table, Map<String, Object> data) {
        if (data.isEmpty()) throw new IllegalStateException("data can not be empty");

        query.append("UPDATE ").append(table);

        data.forEach((key, value) -> {
            if (value instanceof String) {
                if (!query.toString().contains("SET")) {
                    query.append(" SET ").append(key)
                            .append(" = ").append("'").append(value).append("'").append(",");
                } else {
                    query.append(key)
                            .append(" = ").append("'").append(value).append("'").append(",");
                }
            } else {
                if (!query.toString().contains("SET")) {
                    query.append(" SET ").append(key)
                            .append(" = ").append(value).append(",");
                } else {
                    query.append(key)
                            .append(" = ").append(value).append(",");
                }
            }
        });

        query.deleteCharAt(query.lastIndexOf(","));

        return this;
    }

    /**
     * Adds from do query
     *
     * @param table the table name
     */
    private void from(String table) {
        query.append(" FROM ").append(table);
    }

    /**
     * Sets columns
     *
     * @param columnsNames columns to queried
     */
    private void setColumns(String[] columnsNames) {
        if (columnsNames.length == 0) throw new IllegalStateException("columnsNames can not be empty");

        for (String columnName : columnsNames) {
            query.append(columnName).append(",");
        }

        query.deleteCharAt(query.lastIndexOf(","));
    }

    /**
     * @return built query
     */
    public String build() {
        return query.toString();
    }
}
