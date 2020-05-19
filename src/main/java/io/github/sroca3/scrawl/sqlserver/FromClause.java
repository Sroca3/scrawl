package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

public interface FromClause extends TerminatingClause {

    TerminatingClause orderBy(String... columns);

    TerminatingClause where(Condition condition);

    JoinClause join(Table<?> table);
}
