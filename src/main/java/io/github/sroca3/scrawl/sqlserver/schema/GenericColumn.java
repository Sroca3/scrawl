package io.github.sroca3.scrawl.sqlserver.schema;

import io.github.sroca3.scrawl.sqlserver.BalancedCondition;

public class GenericColumn implements Column {
    private final String columnName;

    public GenericColumn(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getName() {
        return columnName;
    }

    @Override
    public BalancedCondition eq(Object parameter) {
        return new BalancedCondition(getName(), "=", parameter);
    }
}
