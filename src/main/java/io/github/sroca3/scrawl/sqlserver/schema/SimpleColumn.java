package io.github.sroca3.scrawl.sqlserver.schema;

import io.github.sroca3.scrawl.sqlserver.BalancedCondition;

public class SimpleColumn implements Column {
    private final String columnName;

    public SimpleColumn(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getName() {
        return columnName;
    }

    @Override
    public Condition eq(Object parameter) {
        return new BalancedCondition(getName(), "=", parameter);
    }

    @Override
    public Condition neq(Object parameter) {
        return new BalancedCondition(getName(), "!=", parameter);
    }
}