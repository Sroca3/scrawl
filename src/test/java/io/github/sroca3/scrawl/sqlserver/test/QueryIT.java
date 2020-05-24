package io.github.sroca3.scrawl.sqlserver.test;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testcontainers.containers.MSSQLServerContainer;

import java.util.Map;

import static io.github.sroca3.scrawl.sqlserver.Query.select;
import static io.github.sroca3.scrawl.sqlserver.Query.star;
import static io.github.sroca3.scrawl.sqlserver.SqlFunction.count;
import static io.github.sroca3.scrawl.sqlserver.schema.CityTable.CITY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryIT {
    private static final MSSQLServerContainer<?> MSSQL_SERVER_CONTAINER = new MSSQLServerContainer<>();
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String ATLANTA = "Atlanta";

    @BeforeAll
    public static void beforeAll() {
        MSSQL_SERVER_CONTAINER.start();
        Flyway flyway = Flyway.configure()
                              .dataSource(
                                  MSSQL_SERVER_CONTAINER.getJdbcUrl(),
                                  MSSQL_SERVER_CONTAINER.getUsername(),
                                  MSSQL_SERVER_CONTAINER.getPassword()
                              )
                              .load();
        flyway.migrate();
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(flyway.getConfiguration().getDataSource());
    }

    @Test
    public void selectWithParameters() {
        var query = select(star()).from(CITY).where(CITY.name().eq(ATLANTA));
        Map<String, ?> map = namedParameterJdbcTemplate.queryForMap(query.getSql(), query.getParameterMap());
        assertEquals(1, map.get("Id"));
        assertEquals(ATLANTA, map.get("Name"));
    }

    @Test
    public void selectCountWhereNameLike() {
        var query = select(count(star())).from(CITY).where(CITY.name().like("B%"));
        Long count = namedParameterJdbcTemplate.queryForObject(query.getSql(), query.getParameterMap(), Long.class);
        assertEquals(2L, count);
    }
}
