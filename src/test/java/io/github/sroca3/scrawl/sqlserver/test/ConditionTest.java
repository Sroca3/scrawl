package io.github.sroca3.scrawl.sqlserver.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.sroca3.scrawl.sqlserver.schema.City.CITY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionTest {

    public static Stream<Arguments> conditionEvaluation() {
        return Stream.of(
            Arguments.of(
                "Name = :name",
                CITY.name().eq(":name").getSql()
            ),
            Arguments.of(
                "Name <> :name",
                CITY.name().neq(":name").getSql()
            ),
            Arguments.of(
                "Name != :name",
                CITY.name().nEq(":name").getSql()
            ),
            Arguments.of(
                "Name > :name",
                CITY.name().gt(":name").getSql()
            ),
            Arguments.of(
                "Name < :name",
                CITY.name().lt(":name").getSql()
            ),
            Arguments.of(
                "Name >= :name",
                CITY.name().gte(":name").getSql()
            ),
            Arguments.of(
                "Name <= :name",
                CITY.name().lte(":name").getSql()
            ),
            Arguments.of(
                "Name !< :name",
                CITY.name().nlt(":name").getSql()
            ),
            Arguments.of(
                "Name !> :name",
                CITY.name().ngt(":name").getSql()
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    public void conditionEvaluation(String expected, String actual) {
        assertEquals(expected, actual);
    }
}
