package io.github.sroca3.scrawl.sqlserver;

public class UnbalancedCondition {
    private String condition;


    public UnbalancedCondition(String condition) {
        this.condition = condition;
    }

    public BalancedCondition rhs(String rhs) {
        return new BalancedCondition(condition + rhs);
    }
}
