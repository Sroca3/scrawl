package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.Expression;

public class SearchCondition implements Expression {

    private String condition;

    public SearchCondition(String lhs) {
        this.condition = lhs;
    }

    public UnbalancedCondition eq() {
        return new UnbalancedCondition(condition + " = ");
    }

    @Override
    public Condition eq(Object parameter) {
        return new BalancedCondition(condition, "=", parameter);
    }

    @Override
    public Condition neq(Object parameter) {
        return new BalancedCondition(condition, "!=", parameter);
    }
}
