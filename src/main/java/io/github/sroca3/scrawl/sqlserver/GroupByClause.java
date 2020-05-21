package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;

public interface GroupByClause extends TerminatingClause {
    HavingClause having(Condition condition);

    TerminatingClause orderBy(Column... columns);
}
