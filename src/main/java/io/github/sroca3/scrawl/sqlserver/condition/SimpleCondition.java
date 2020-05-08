package io.github.sroca3.scrawl.sqlserver.condition;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;

import java.util.Locale;

public class SimpleCondition extends AbstractCondition implements Condition {
    private String condition;
    private String lhs;
    private String operator;
    private Object parameter;

    public SimpleCondition(String condition) {
        this.condition = condition;
    }

    public SimpleCondition(String lhs, String operator, Object parameter) {
        this.lhs = lhs;
        this.operator = operator;
        this.parameter = parameter;
    }

    public String build() {
        return this.condition;
    }

    @Override
    public String getSql() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.lhs)
               .append(SPACE)
               .append(this.operator)
               .append(SPACE);
        if (this.parameter instanceof String && String.valueOf(this.parameter).startsWith(":")) {
            builder.append(this.parameter);
        } else {
            String parameterName = getParameters().generateParameterName(this.lhs.toLowerCase(Locale.ENGLISH));
            getParameters().addParameter(parameterName, this.parameter);
            builder.append(parameterName);
        }
        return builder.toString();
    }
}
