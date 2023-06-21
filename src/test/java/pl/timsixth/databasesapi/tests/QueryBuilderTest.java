package pl.timsixth.databasesapi.tests;

import org.junit.Test;
import pl.timsixth.databasesapi.database.query.QueryBuilder;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

    @Test
    public void shouldGenerateSelectQueryWithColumns() {
        QueryBuilder queryBuilder = new QueryBuilder();

        String select = queryBuilder.select("users", "username", "password").build();

        assertEquals("SELECT username,password FROM users", select);
    }

    @Test
    public void shouldGenerateSelectQueryWithAllColumns() {
        QueryBuilder queryBuilder = new QueryBuilder();

        String select = queryBuilder.selectAll("users").build();

        assertEquals("SELECT * FROM users", select);
    }

    @Test
    public void shouldGenerateDeleteQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();

        String query = queryBuilder.delete("users", "password", "username").build();

        assertEquals("DELETE password,username FROM users", query);
    }

    @Test
    public void shouldGenerateDeleteQueryWithAllColumns() {
        QueryBuilder queryBuilder = new QueryBuilder();

        String query = queryBuilder.deleteAll("users").build();

        assertEquals("DELETE FROM users", query);
    }

    @Test
    public void shouldGenerateDeleteQueryWithAllColumnsWithWhere() {
        QueryBuilder queryBuilder = new QueryBuilder();

        String query = queryBuilder.deleteAll("users").where("username = 'test'").build();

        assertEquals("DELETE FROM users WHERE username = 'test'", query);
    }

    @Test
    public void shouldGenerateInsertIntoQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();

        String query = queryBuilder.insert("users", null, "user", 12).build();

        assertEquals("INSERT INTO users VALUES(null,'user',12)", query);
    }

    @Test
    public void shouldGenerateUpdateQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();

        Map<String, Object> data = new HashMap<>();
        data.put("username", "test");
        data.put("age", 12);

        String query = queryBuilder.update("users", data).build();

        assertEquals("UPDATE users SET age = 12,username = 'test'", query);
    }

    @Test
    public void shouldGenerateInesrtQueryWithListInArgs() {
        QueryBuilder queryBuilder = new QueryBuilder();

        List<Object> data = new ArrayList<>();
        data.add("test");
        data.add(12);
        data.add(UUID.fromString("4af94c03-9cbd-49f4-a77e-421a2c146160"));

        String query = queryBuilder.insert("test", null, data).build();

        assertEquals("INSERT INTO test VALUES(null,'test',12,'4af94c03-9cbd-49f4-a77e-421a2c146160')", query);
    }

    @Test
    public void shouldGenerateUpdateWithOneSetWithTwoWhereConditionsQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();

        UUID uuid = UUID.fromString("4af94c03-9cbd-49f4-a77e-421a2c146160");
        String group = "Admin";

        Map<String, Object> data = new HashMap<>();
        data.put("username", "test");

        String query = queryBuilder.update("users", data)
                .where("uuid = '" + uuid + "' AND group = '" + group + "'")
                .build();

        assertEquals("UPDATE users SET username = 'test' WHERE uuid = '4af94c03-9cbd-49f4-a77e-421a2c146160' AND group = 'Admin'", query);
    }
}
