package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Table;

public interface SelectColumnsClause {

    FromClause from(String tableName);

    FromClause from(Table<?> table);
}
