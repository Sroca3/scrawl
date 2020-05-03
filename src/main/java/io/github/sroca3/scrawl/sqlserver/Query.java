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
        return new SelectClause();
    }

    public static SelectColumnsClause select(String... columns) {
        if (columns.length < 1) {
            throw new IllegalArgumentException("Columns cannot be empty.");
        }
        List<String> cols = Arrays.stream(columns).filter(c -> !c.isBlank()).collect(Collectors.toList());
        if (columns.length > cols.size()) {
            throw new IllegalArgumentException("Cannot specify blank column names");
        }
        return new SelectColumnsClause(cols, true);
    }

    public static SelectColumnsClause select(Column... columns) {
        if (columns.length < 1) {
            throw new IllegalArgumentException("Columns cannot be empty.");
        }
        List<Column> cols = Arrays.stream(columns).filter(Objects::nonNull).collect(Collectors.toList());
        if (columns.length > cols.size()) {
            throw new IllegalArgumentException("Cannot specify blank column names");
        }
        return new SelectColumnsClause(cols);
    }

    public static SearchCondition lhs(String lhs) {
        return new SearchCondition(lhs);
    }
}
