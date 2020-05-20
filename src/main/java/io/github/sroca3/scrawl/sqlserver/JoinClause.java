package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

public interface JoinClause {
    FromClause on(Condition condition);
}
