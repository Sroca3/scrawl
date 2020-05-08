package io.github.sroca3.scrawl.sqlserver.condition;

import io.github.sroca3.scrawl.sqlserver.schema.Condition;

public class CombiningCondition extends AbstractCondition implements Condition {
    private final Condition leftCondition;
    private final String logicalOperator;
    private final Condition rightCondition;

    public CombiningCondition(Condition leftCondition, String logicalOperator, Condition rightCondition) {
        this.leftCondition = leftCondition;
        this.logicalOperator = logicalOperator;
        this.rightCondition = rightCondition;
    }

    @Override
    public String getSql() {
        this.leftCondition.setInitialParameters(this.getParameters());
        String sql = this.leftCondition.getSql();
        this.rightCondition.setInitialParameters(this.leftCondition.getParameters());
        sql += SPACE + logicalOperator + SPACE;
        sql += this.rightCondition.getSql();
        return sql;
    }
}
