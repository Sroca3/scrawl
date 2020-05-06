package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;

import java.util.LinkedList;
import java.util.List;

public class BalancedCondition implements Condition {
    private String condition;
    private String lhs;
    private String operator;
    private Object parameter;
    private String logicalOperator;
    private final List<Condition> conditions = new LinkedList<>();

    public BalancedCondition(String condition) {
        this.condition = condition;
    }

    public BalancedCondition(String lhs, String operator, Object parameter) {
        this.lhs = lhs;
        this.operator = operator;
        this.parameter = parameter;
    }

    public String getLhs() {
        return lhs;
    }

    public String getOperator() {
        return operator;
    }

    public Object getParameter() {
        return parameter;
    }

    public String build() {
        return this.condition;
    }

    @Override
    public Condition or(Condition condition) {
        ((BalancedCondition)condition).setLogicalOperator("OR");
        conditions.add(condition);
        return this;
    }

    @Override
    public Condition and(Condition condition) {
        ((BalancedCondition)condition).setLogicalOperator("AND");
        conditions.add(condition);
        return this;
    }

    private void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public List<Condition> getConditions() {
        return List.copyOf(conditions);
    }

    public String getLogicalOperator() {
        return this.logicalOperator;
    }
}
