package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueryBuilder implements SelectColumnsClause, FromClause, JoinClause, GroupByClause, HavingClause, TerminatingClause {

    private final SqlBuilder sqlBuilder;

    public QueryBuilder() {
        this.sqlBuilder = new SqlBuilder();
    }

    public QueryBuilder(String... columns) {
        this();
        List<String> cols = Arrays.stream(columns).filter(c -> !c.isBlank()).collect(Collectors.toList());
        if (columns.length > cols.size()) {
            throw new IllegalArgumentException("Cannot specify blank column names");
        }
        this.sqlBuilder.addColumnNames(cols);
    }

    public QueryBuilder(Column[] columns) {
        this();
        List<Column> cols = Arrays.stream(columns)
                                  .filter(Objects::nonNull)
                                  .filter(c -> !c.getName().isBlank())
                                  .collect(Collectors.toList());
        if (columns.length > cols.size()) {
            throw new IllegalArgumentException("Cannot specify blank column names");
        }
        this.sqlBuilder.addColumns(cols);
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
    public TerminatingClause where(Condition condition) {
        sqlBuilder.addConditionToWhereClause(condition);
        return this;
    }

    @Override
    public JoinClause join(Table<?> table) {
        sqlBuilder.addJoinClause(JoinType.INNER, table);
        return this;
    }

    @Override
    public JoinClause leftJoin(Table<?> table) {
        sqlBuilder.addJoinClause(JoinType.LEFT, table);
        return this;
    }

    @Override
    public JoinClause rightJoin(Table<?> table) {
        sqlBuilder.addJoinClause(JoinType.RIGHT, table);
        return this;
    }

    @Override
    public JoinClause outerJoin(Table<?> table) {
        sqlBuilder.addJoinClause(JoinType.OUTER, table);
        return this;
    }

    @Override
    public GroupByClause groupBy(Column column) {
        sqlBuilder.addGroupByClause(column);
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

    @Override
    public HavingClause having(Condition condition) {
        sqlBuilder.addHavingClause(condition);
        return this;
    }

    @Override
    public TerminatingClause orderBy(Column... columns) {
        sqlBuilder.addOrderByClause(columns);
        return this;
    }
}
