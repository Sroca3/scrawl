package io.github.sroca3.scrawl.sqlserver;

import java.util.LinkedList;
import java.util.List;

public class BalancedCondition {
    private String condition;
    private String lhs;
    private String operator;
    private Object parameter;
    private String logicalOperator;
    private final List<BalancedCondition> conditions = new LinkedList<>();

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

    public BalancedCondition or(BalancedCondition condition) {
        condition.setLogicalOperator("OR");
        conditions.add(condition);
        return this;
    }

    public BalancedCondition and(BalancedCondition condition) {
        condition.setLogicalOperator("AND");
        conditions.add(condition);
        return this;
    }

    private void setLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public List<BalancedCondition> getConditions() {
        return List.copyOf(conditions);
    }

    public String getLogicalOperator() {
        return this.logicalOperator;
    }
}
