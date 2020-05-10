package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.condition.SimpleCondition;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Expression;
import io.github.sroca3.scrawl.sqlserver.schema.Operator;

public class SearchCondition implements Expression {

    private String lhs;

    public SearchCondition(String lhs) {
        this.lhs = lhs;
    }

    public UnbalancedCondition eq() {
        return new UnbalancedCondition(lhs + " = ");
    }

    @Override
    public Condition eq(Object parameter) {
        return new SimpleCondition(lhs, Operator.EQ.getOperator(), parameter);
    }

    @Override
    public Condition neq(Object parameter) {
        return new SimpleCondition(lhs, Operator.NEQ.getOperator(), parameter);
    }

    @Override
    public Condition nEq(Object parameter) {
        return new SimpleCondition(lhs, Operator.NEQ1.getOperator(), parameter);
    }

    @Override
    public Condition gt(Object parameter) {
        return new SimpleCondition(lhs, Operator.GT.getOperator(), parameter);
    }

    @Override
    public Condition lt(Object parameter) {
        return new SimpleCondition(lhs, Operator.LT.getOperator(), parameter);
    }

    @Override
    public Condition gte(Object parameter) {
        return new SimpleCondition(lhs, Operator.GTE.getOperator(), parameter);
    }

    @Override
    public Condition lte(Object parameter) {
        return new SimpleCondition(lhs, Operator.LTE.getOperator(), parameter);
    }

    @Override
    public Condition nlt(Object parameter) {
        return new SimpleCondition(lhs, Operator.NLT.getOperator(), parameter);
    }

    @Override
    public Condition ngt(Object parameter) {
        return new SimpleCondition(lhs, Operator.NGT.getOperator(), parameter);
    }

    @Override
    public Condition like(String parameter) {
        return new SimpleCondition(lhs, Operator.LIKE.getOperator(), parameter);
    }
}
