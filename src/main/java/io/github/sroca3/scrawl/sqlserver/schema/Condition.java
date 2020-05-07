package io.github.sroca3.scrawl.sqlserver.schema;

import io.github.sroca3.scrawl.sqlserver.ParametersHolder;

public interface Condition extends SqlHolder, ParametersHolder {
    Condition and(Condition condition);

    Condition or(Condition condition);
}
