package io.github.sroca3.scrawl.sqlserver.schema;

public interface Expression {
    Condition eq(Object parameter);

    Condition neq(Object parameter);
}
