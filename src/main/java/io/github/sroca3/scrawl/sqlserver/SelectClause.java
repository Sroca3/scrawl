package io.github.sroca3.scrawl.sqlserver;

import java.util.List;
import java.util.Map;

public class SelectClause implements TerminatingClause {
    private final SqlBuilder sqlBuilder = new SqlBuilder();

    public SelectClause() {
        this.sqlBuilder.markAsSelectQuery();
    }

    public TerminatingClause one() {
        sqlBuilder.addColumnNames(List.of("1"));
        return this;
    }

    public SelectColumnsClause star() {
        return new SelectColumnsClause(List.of("*"), true);
    }

    @Override
    public String getSql() {
        return sqlBuilder.build();
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return sqlBuilder.getParameterMap();
    }

    public SelectColumnsClause count() {
        return new SelectColumnsClause(List.of("COUNT(*)"), true);
    }
}
