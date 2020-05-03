package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

import java.util.List;

public class SelectColumnsClause {
    private final SqlBuilder sqlBuilder = new SqlBuilder();

    public SelectColumnsClause(List<String> columns, boolean stuff) {
        this.sqlBuilder.markAsSelectQuery();
        this.sqlBuilder.addColumnNames(columns);
    }

    public SelectColumnsClause(List<Column> columns) {
        this.sqlBuilder.markAsSelectQuery();
        this.sqlBuilder.addColumns(columns);
    }

    public FromClause from(String tableName) {
        return new FromClause(this.sqlBuilder, tableName);
    }

    public FromClause from(Table<?> table) {
        return new FromClause(this.sqlBuilder, table);
    }
}
