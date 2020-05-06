package io.github.sroca3.scrawl.sqlserver.schema;

public interface Condition {
    Condition and(Condition condition);

    Condition or(Condition condition);
}
