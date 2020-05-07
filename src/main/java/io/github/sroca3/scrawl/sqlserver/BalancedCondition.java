package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class BalancedCondition implements Condition {
    private String condition;
    private String lhs;
    private String operator;
    private Object parameter;
    private String logicalOperator;
    private Condition nextCondition;
    private final List<Condition> conditions = new LinkedList<>();
    private final Parameters parameters = new Parameters();

    public BalancedCondition(String condition) {
        this.condition = condition;
    }

    public BalancedCondition(String lhs, String operator, Object parameter) {
        this.lhs = lhs;
        this.operator = operator;
        this.parameter = parameter;
    }

    public String build() {
        return this.condition;
    }

    @Override
    public Condition or(Condition condition) {
        if (nextCondition != null) {
            this.nextCondition.or(condition);
            return this;
        }
        this.logicalOperator = "OR";
        conditions.add(condition);
        nextCondition = condition;
        return this;
    }

    @Override
    public Condition and(Condition condition) {
        if (nextCondition != null) {
            this.nextCondition.and(condition);
            return this;
        }
        this.logicalOperator = "AND";
        conditions.add(condition);
        nextCondition = condition;
        return this;
    }

    @Override
    public String getSql() {
        StringBuilder builder = new StringBuilder(getSqlForCondition());
//        conditions.forEach(c -> {
//            c.setInitialParameters(this.parameters);
//            builder.append(c.getSql());
//        });
if(this.nextCondition != null){
    this.nextCondition.setInitialParameters(parameters);
        builder.append(this.nextCondition.getSql());
}
        return builder.toString();
    }

    private static final String SPACE = " ";

    private String getSqlForCondition() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.lhs)
               .append(SPACE)
               .append(this.operator)
               .append(SPACE);
        if (this.parameter instanceof String && String.valueOf(this.parameter).startsWith(":")) {
            builder.append(this.parameter);
        } else {
            String parameterName = parameters.generateParameterName(this.lhs.toLowerCase(Locale.ENGLISH));
            parameters.addParameter(parameterName, this.parameter);
            builder.append(parameterName);
        }
        if (this.logicalOperator != null) {
            builder.append(SPACE)
                   .append(this.logicalOperator)
                   .append(SPACE);
        }
        return builder.toString();
    }

    @Override
    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public void setInitialParameters(Parameters parameters) {
        this.parameters.addParameters(parameters);
    }
}
