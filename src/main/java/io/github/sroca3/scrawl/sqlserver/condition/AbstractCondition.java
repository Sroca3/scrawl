package io.github.sroca3.scrawl.sqlserver.condition;

import io.github.sroca3.scrawl.sqlserver.Parameters;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;

public abstract class AbstractCondition implements Condition {
    protected static final String SPACE = " ";
    private final Parameters parameters = new Parameters();

    @Override
    public Parameters getParameters() {
        return parameters;
    }

    @Override
    public void setInitialParameters(Parameters parameters) {
        this.parameters.addParameters(parameters);
    }

    @Override
    public Condition or(Condition condition) {
        return new CombiningCondition(this, "OR", condition);
    }

    @Override
    public Condition and(Condition condition) {
        return new CombiningCondition(this, "AND", condition);
    }
}
