package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueryBuilder implements SelectClause, SelectColumnsClause, FromClause, JoinClause, TerminatingClause {

    private final SqlBuilder sqlBuilder;

    public QueryBuilder() {
        this.sqlBuilder = new SqlBuilder();
        sqlBuilder.markAsSelectQuery();
    }

    public QueryBuilder(String... columns) {
        this();
        if (columns.length < 1) {
            throw new IllegalArgumentException("Columns cannot be empty.");
        }
        List<String> cols = Arrays.stream(columns).filter(c -> !c.isBlank()).collect(Collectors.toList());
        if (columns.length > cols.size()) {
            throw new IllegalArgumentException("Cannot specify blank column names");
        }
        this.sqlBuilder.addColumnNames(cols);
    }

    public QueryBuilder(Column[] columns) {
        this();
        if (columns.length < 1) {
            throw new IllegalArgumentException("Columns cannot be empty.");
        }
        List<Column> cols = Arrays.stream(columns).filter(Objects::nonNull).collect(Collectors.toList());
        if (columns.length > cols.size()) {
            throw new IllegalArgumentException("Cannot specify blank column names");
        }
        this.sqlBuilder.addColumns(cols);
    }

    public TerminatingClause one() {
        sqlBuilder.addColumnNames(List.of("1"));
        return this;
    }

    @Override
    public SelectColumnsClause star() {
        sqlBuilder.addColumnNames(List.of("*"));
        return this;
    }

    @Override
    public SelectColumnsClause count() {
        sqlBuilder.addColumnNames(List.of("COUNT(*)"));
        return this;
    }

    @Override
    public FromClause from(String tableName) {
        this.sqlBuilder.addRootTable(tableName);
        return this;
    }

    @Override
    public FromClause from(Table<?> table) {
        this.sqlBuilder.addRootTable(table);
        return this;
    }

    @Override
    public TerminatingClause orderBy(String... columns) {
        sqlBuilder.addOrderByClause(columns);
        return this;
    }

    @Override
    public TerminatingClause where(String whereClause) {
        sqlBuilder.addWhereClause(whereClause);
        return this;
    }

    @Override
    public TerminatingClause where(Condition condition) {
        sqlBuilder.addConditionToWhereClause(condition);
        return this;
    }

    @Override
    public JoinClause join(Table<?> table) {
        sqlBuilder.addJoinClause(table);
        return this;
    }

    @Override
    public FromClause on(Condition condition) {
        sqlBuilder.addJoinCondition(condition);
        return this;
    }

    @Override
    public String getSql() {
        return sqlBuilder.build();
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return sqlBuilder.getParameterMap();
    }
}
