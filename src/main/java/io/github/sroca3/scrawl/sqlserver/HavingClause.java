package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;

public interface HavingClause extends TerminatingClause {
    TerminatingClause orderBy(Column... columns);
}
