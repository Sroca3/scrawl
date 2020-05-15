package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;

public interface JoinClause {
    FromClause on(Condition condition);
}
