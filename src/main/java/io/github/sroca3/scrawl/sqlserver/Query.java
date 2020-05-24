package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Expression;
import io.github.sroca3.scrawl.sqlserver.schema.SimpleColumn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Query {

    private Query() {
    }

    public static Column star() {
        return new SimpleColumn("*");
    }

    public static TerminatingClause selectOne() {
        return new TerminatingClause() {
            @Override
            public String getSql() {
                return "SELECT 1";
            }
        };
    }

    public static SelectColumnsClause select(String... columns) {
        return new QueryBuilder(columns);
    }

    public static SelectColumnsClause select(Column... columns) {
        return new QueryBuilder(columns);
    }

    public static Expression lhs(String lhs) {
        return new SimpleColumn(lhs);
    }
}
