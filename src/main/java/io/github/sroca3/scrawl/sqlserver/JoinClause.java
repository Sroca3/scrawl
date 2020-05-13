package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

public class JoinClause {
    private final SqlBuilder sqlBuilder;

    public JoinClause(SqlBuilder sqlBuilder, Table<?> table) {
        this.sqlBuilder = sqlBuilder;
    }

    public FromClause on(Condition condition) {
        sqlBuilder.addJoinCondition(condition);
        return new FromClause(this.sqlBuilder);
    }
}
