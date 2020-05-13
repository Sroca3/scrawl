package io.github.sroca3.scrawl.sqlserver.test;

import io.github.sroca3.scrawl.sqlserver.schema.CityTable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static io.github.sroca3.scrawl.sqlserver.Query.lhs;
import static io.github.sroca3.scrawl.sqlserver.Query.select;
import static io.github.sroca3.scrawl.sqlserver.schema.CityTable.CITY;
import static io.github.sroca3.scrawl.sqlserver.schema.Permission.PERMISSIONS;
import static io.github.sroca3.scrawl.sqlserver.schema.RoleTable.ROLE;
import static io.github.sroca3.scrawl.sqlserver.schema.UserTable.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueryTest {

    public static Stream<Arguments> sqlEvaluation() {
        CityTable c = CITY.as("c");
        return Stream.of(
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select().star().from("City").where(lhs("Country").eq().rhs(":country").build()).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select().star().from("City").where(lhs("Country").eq(":country")).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select().star().from("City").where(lhs("Country").eq().rhs(":country").build()).getSql()
            ),
            Arguments.of(
                "SELECT 1",
                select().one().getSql()
            ),
            Arguments.of(
                "SELECT * FROM table",
                select().star().from("table").build()
            ),
            Arguments.of(
                "SELECT * FROM City",
                select().star().from(CITY).build()
            ),
            Arguments.of(
                "SELECT * FROM City c",
                select().star().from(CITY.as("c")).build()
            ),
            Arguments.of(
                "SELECT Country, State, City FROM City",
                select("Country", "State", "City").from("City").build()
            ),
            Arguments.of(
                "SELECT * FROM City ORDER BY CreateDt",
                select().star().from("City").orderBy("CreateDt").getSql()
            ),
            Arguments.of(
                "SELECT Country, State, City FROM City ORDER BY State ASC, Country",
                select("Country", "State", "City").from("City").orderBy("State ASC", "Country").getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select().star().from("City").where(lhs("Country").eq(":country")).getSql()
            ),
            Arguments.of(
                "SELECT c.Name FROM City c",
                select(c.name()).from(c).build()
            ),
            Arguments.of(
                "SELECT c.Name FROM City c",
                select(c.columns()).from(c).build()
            ),
            Arguments.of(
                "SELECT * FROM IAM.Permissions",
                select().star().from(PERMISSIONS).build()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Name = :name",
                select().star().from(CITY).where(CITY.name().eq("Atlanta")).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Name = :name OR Name = :name1",
                select().star()
                        .from(CITY)
                        .where(
                            CITY.name().eq("Atlanta").or(CITY.name().eq("Baltimore"))
                        )
                        .getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Name = :name OR Name = :name1 AND Name <> :name2",
                select().star()
                        .from(CITY)
                        .where(
                            CITY.name().eq("Atlanta")
                                .or(CITY.name().eq("Baltimore")
                                        .and(CITY.name().neq("Washington")))
                        )
                        .getSql()
            ),
            Arguments.of(
                "SELECT Name FROM IAM.User JOIN IAM.Role ON Id = RoleId",
                select(ROLE.name())
                    .from(USER)
                    .join(ROLE).on(ROLE.id().eq(USER.roleId()))
                    .getSql()
            )
        );
    }

    @Test
    public void selectBlankColumnsFromTable() {
        assertThrows(IllegalArgumentException.class, () -> select(""));
    }

    @Test
    public void parameterMap() {
        Map<String, Object> parameters = select().star().from(CITY).where(CITY.name().eq("Atlanta")).getParameterMap();
        assertEquals("name", parameters.keySet().iterator().next());
        assertEquals("Atlanta", parameters.get("name"));
    }

    @ParameterizedTest
    @MethodSource
    public void sqlEvaluation(String expected, String actual) {
        assertEquals(expected, actual);
    }
}
