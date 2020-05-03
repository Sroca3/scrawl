package io.github.sroca3.scrawl.sqlserver.schema;

import io.github.sroca3.scrawl.sqlserver.BalancedCondition;

public interface Condition {
    BalancedCondition eq(Object parameter);
}
