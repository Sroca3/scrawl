package io.github.sroca3.scrawl.sqlserver.schema;

import io.github.sroca3.scrawl.sqlserver.TerminatingClause;
import io.github.sroca3.scrawl.sqlserver.condition.DirectCondition;
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
    public Condition eq(Column column) {
        return new DirectCondition(getName(), Operator.EQ.getOperator(), column.getName());
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

    @Override
    public Condition like(String parameter) {
        return new SimpleCondition(getName(), Operator.LIKE.getOperator(), parameter);
    }

    @Override
    public Column as(String alias) {
        return new AliasedColumn(this, alias);
    }

    @Override
    public Condition in(String parameter) {
        return new SimpleCondition(getName(), Operator.IN.getOperator(), parameter);
    }

    @Override
    public Condition notIn(TerminatingClause terminatingClause) {
        return new DirectCondition(getName(), Operator.NOT_IN.getOperator(), "(" + terminatingClause.getSql() + ")");
    }


}
