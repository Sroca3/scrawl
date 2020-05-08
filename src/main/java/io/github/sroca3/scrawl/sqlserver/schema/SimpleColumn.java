package io.github.sroca3.scrawl.sqlserver.schema;

import io.github.sroca3.scrawl.sqlserver.condition.SimpleCondition;

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
        return new SimpleCondition(getName(), Operator.EQ.getOperator(), parameter);
    }

    @Override
    public Condition neq(Object parameter) {
        return new SimpleCondition(getName(), Operator.NEQ.getOperator(), parameter);
    }

    @Override
    public Condition nEq(Object parameter) {
        return new SimpleCondition(getName(), Operator.NEQ1.getOperator(), parameter);
    }

    @Override
    public Condition gt(Object parameter) {
        return new SimpleCondition(getName(), Operator.GT.getOperator(), parameter);
    }

    @Override
    public Condition lt(Object parameter) {
        return new SimpleCondition(getName(), Operator.LT.getOperator(), parameter);
    }

    @Override
    public Condition gte(Object parameter) {
        return new SimpleCondition(getName(), Operator.GTE.getOperator(), parameter);
    }

    @Override
    public Condition lte(Object parameter) {
        return new SimpleCondition(getName(), Operator.LTE.getOperator(), parameter);
    }

    @Override
    public Condition nlt(Object parameter) {
        return new SimpleCondition(getName(), Operator.NLT.getOperator(), parameter);
    }

    @Override
    public Condition ngt(Object parameter) {
        return new SimpleCondition(getName(), Operator.NGT.getOperator(), parameter);
    }
}
