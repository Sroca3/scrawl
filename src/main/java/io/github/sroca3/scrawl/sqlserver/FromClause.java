package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

import java.util.Map;

public class FromClause implements TerminatingClause {

    private SqlBuilder sqlBuilder;

    public FromClause(SqlBuilder sqlBuilder, String tableName) {
        this.sqlBuilder = sqlBuilder;
        this.sqlBuilder.addRootTable(tableName);
    }

    public FromClause(SqlBuilder sqlBuilder, Table<?> table) {
        this.sqlBuilder = sqlBuilder;
        this.sqlBuilder.addRootTable(table);
    }

    public String build() {
        return sqlBuilder.build();
    }

    public TerminatingClause orderBy(String... columns) {
        sqlBuilder.addOrderByClause(columns);
        return this;
    }

    public TerminatingClause where(String whereClause) {
        sqlBuilder.addWhereClause(whereClause);
        return this;
    }

    public TerminatingClause where(Condition condition) {
        sqlBuilder.addConditionToWhereClause(condition);
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
