package io.github.sroca3.scrawl.sqlserver.test;

import io.github.sroca3.scrawl.sqlserver.schema.CityTable;
import io.github.sroca3.scrawl.sqlserver.schema.DepartmentsTable;
import io.github.sroca3.scrawl.sqlserver.schema.EmployeesTable;
import io.github.sroca3.scrawl.sqlserver.schema.SimpleColumn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static io.github.sroca3.scrawl.sqlserver.Query.lhs;
import static io.github.sroca3.scrawl.sqlserver.Query.select;
import static io.github.sroca3.scrawl.sqlserver.Query.selectOne;
import static io.github.sroca3.scrawl.sqlserver.Query.star;
import static io.github.sroca3.scrawl.sqlserver.SqlFunction.count;
import static io.github.sroca3.scrawl.sqlserver.schema.CityTable.CITY;
import static io.github.sroca3.scrawl.sqlserver.schema.DepartmentsTable.DEPARTMENTS;
import static io.github.sroca3.scrawl.sqlserver.schema.EmployeesTable.EMPLOYEES;
import static io.github.sroca3.scrawl.sqlserver.schema.Permission.PERMISSIONS;
import static io.github.sroca3.scrawl.sqlserver.schema.RoleTable.ROLE;
import static io.github.sroca3.scrawl.sqlserver.schema.UserTable.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryTest {

    public static Stream<Arguments> sqlEvaluation() {
        CityTable c = CITY.as("c");
        return Stream.of(
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select(star()).from("City").where(lhs("Country").eq(":country")).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select(star()).from("City").where(lhs("Country").eq(":country")).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select(star()).from("City").where(lhs("Country").eq(":country")).getSql()
            ),
            Arguments.of(
                "SELECT 1",
                selectOne().getSql()
            ),
            Arguments.of(
                "SELECT * FROM table",
                select(star()).from("table").getSql()
            ),
            Arguments.of(
                "SELECT * FROM City",
                select(star()).from(CITY).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City c",
                select(star()).from(CITY.as("c")).getSql()
            ),
            Arguments.of(
                "SELECT Country, State, City FROM City",
                select("Country", "State", "City").from("City").getSql()
            ),
            Arguments.of(
                "SELECT * FROM City ORDER BY CreateDt",
                select(star()).from("City").orderBy("CreateDt").getSql()
            ),
            Arguments.of(
                "SELECT Country, State, City FROM City ORDER BY State ASC, Country",
                select("Country", "State", "City").from("City").orderBy("State ASC", "Country").getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Country = :country",
                select(star()).from("City").where(lhs("Country").eq(":country")).getSql()
            ),
            Arguments.of(
                "SELECT c.Name FROM City c",
                select(c.name()).from(c).getSql()
            ),
            Arguments.of(
                "SELECT c.Name FROM City c",
                select(c.columns()).from(c).getSql()
            ),
            Arguments.of(
                "SELECT * FROM IAM.Permissions",
                select(star()).from(PERMISSIONS).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Name = :name",
                select(star()).from(CITY).where(CITY.name().eq("Atlanta")).getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Name = :name OR Name = :name1",
                select(star())
                        .from(CITY)
                        .where(
                            CITY.name().eq("Atlanta").or(CITY.name().eq("Baltimore"))
                        )
                        .getSql()
            ),
            Arguments.of(
                "SELECT * FROM City WHERE Name = :name OR Name = :name1 AND Name <> :name2",
                select(star())
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
            ),
            Arguments.of(
                "SELECT Name FROM IAM.User LEFT JOIN IAM.Role ON Id = RoleId",
                select(ROLE.name())
                    .from(USER)
                    .leftJoin(ROLE).on(ROLE.id().eq(USER.roleId()))
                    .getSql()
            ),
            Arguments.of(
                "SELECT Name FROM IAM.User RIGHT JOIN IAM.Role ON Id = RoleId",
                select(ROLE.name())
                    .from(USER)
                    .rightJoin(ROLE).on(ROLE.id().eq(USER.roleId()))
                    .getSql()
            ),
            Arguments.of(
                "SELECT Name FROM IAM.User OUTER JOIN IAM.Role ON Id = RoleId",
                select(ROLE.name())
                    .from(USER)
                    .outerJoin(ROLE).on(ROLE.id().eq(USER.roleId()))
                    .getSql()
            ),
            Arguments.of(
                "SELECT RoleId, COUNT(Username) FROM IAM.User GROUP BY RoleId",
                select(USER.roleId(), count(USER.username()))
                    .from(USER)
                    .groupBy(USER.roleId()).getSql()
            ),
            Arguments.of(
                "SELECT RoleId, COUNT(Username) FROM IAM.User GROUP BY RoleId HAVING COUNT(RoleId) > :numberOfRoleIds",
                select(USER.roleId(), count(USER.username()))
                    .from(USER)
                    .groupBy(USER.roleId())
                    .having(count(USER.roleId()).gt(":numberOfRoleIds"))
                    .getSql()
            ),
            Arguments.of(
                "SELECT RoleId, COUNT(Username) FROM IAM.User GROUP BY RoleId HAVING COUNT(RoleId) > :numberOfRoleIds ORDER BY Username",
                select(USER.roleId(), count(USER.username()))
                    .from(USER)
                    .groupBy(USER.roleId())
                    .having(count(USER.roleId()).gt(":numberOfRoleIds"))
                    .orderBy(USER.username())
                    .getSql()
            ),
            Arguments.of(
                "SELECT RoleId, COUNT(Username) FROM IAM.User GROUP BY RoleId ORDER BY Username",
                select(USER.roleId(), count(USER.username()))
                    .from(USER)
                    .groupBy(USER.roleId())
                    .orderBy(USER.username())
                    .getSql()
            ),
            Arguments.of(
                "SELECT COUNT(Id) AS numberOfRoles FROM IAM.Role",
                select(count(ROLE.id()).as("numberOfRoles")).from(ROLE).getSql()
            ),
            Arguments.of(
                "SELECT Id FROM IAM.Role WHERE Name IN (:roleNames)",
                select(ROLE.id()).from(ROLE).where(ROLE.name().in(":roleNames")).getSql()
            ),
            Arguments.of(
                "SELECT Name FROM Employees WHERE DepartmentId NOT IN (SELECT Id FROM Employees)",
                select(EMPLOYEES.name()).from(EMPLOYEES).where(EMPLOYEES.departmentId().notIn(select(DEPARTMENTS.id()).from(DEPARTMENTS))).getSql()
            )
        );
    }

    @Test
    public void selectBlankColumnsFromTable() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> select(""));
        assertEquals("Cannot specify blank column names", illegalArgumentException.getMessage());
        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> select(new SimpleColumn("")));
        assertEquals("Cannot specify blank column names", illegalArgumentException.getMessage());
    }

    @Test
    public void parameterMap() {
        Map<String, Object> parameters = select(star()).from(CITY).where(CITY.name().eq("Atlanta")).getParameterMap();
        assertEquals("name", parameters.keySet().iterator().next());
        assertEquals("Atlanta", parameters.get("name"));
        assertTrue(selectOne().getParameterMap().isEmpty());
    }

    @ParameterizedTest
    @MethodSource
    public void sqlEvaluation(String expected, String actual) {
        assertEquals(expected, actual);
    }
}
