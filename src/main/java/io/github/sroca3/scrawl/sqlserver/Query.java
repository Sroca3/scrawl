package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Query {

    private Query() {
    }

    public static SelectClause select() {
        return new QueryBuilder();
    }

    public static SelectColumnsClause select(String... columns) {
        return new QueryBuilder(columns);
    }

    public static SelectColumnsClause select(Column... columns) {
        return new QueryBuilder(columns);
    }

    public static SearchCondition lhs(String lhs) {
        return new SearchCondition(lhs);
    }
}
