package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;

public class SearchCondition implements Condition {

    private String condition;

    public SearchCondition(String lhs) {
        this.condition = lhs;
    }

    public UnbalancedCondition eq() {
        return new UnbalancedCondition(condition + " = ");
    }

    @Override
    public BalancedCondition eq(Object parameter) {
        return new BalancedCondition(condition + " = " + parameter);
    }
}
