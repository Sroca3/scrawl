package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.condition.SimpleCondition;

public class UnbalancedCondition {
    private String condition;


    public UnbalancedCondition(String condition) {
        this.condition = condition;
    }

    public SimpleCondition rhs(String rhs) {
        return new SimpleCondition(condition + rhs);
    }
}
