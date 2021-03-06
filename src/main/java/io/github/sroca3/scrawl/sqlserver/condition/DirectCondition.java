package io.github.sroca3.scrawl.sqlserver.condition;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;

import java.util.Locale;

public class DirectCondition extends AbstractCondition implements Condition {
    private String lhs;
    private String operator;
    private String rhs;

    public DirectCondition(String lhs, String operator, String rhs) {
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    @Override
    public String getSql() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.lhs)
               .append(SPACE)
               .append(this.operator)
               .append(SPACE)
               .append(this.rhs);
        return builder.toString();
    }
}
