package io.github.sroca3.scrawl.sqlserver.schema;

public interface Expression {
    Condition eq(Column column);

    Condition eq(Object parameter);

    Condition neq(Object parameter);

    Condition nEq(Object parameter);

    Condition gt(Object parameter);

    Condition lt(Object parameter);

    Condition gte(Object parameter);

    Condition lte(Object parameter);

    Condition nlt(Object parameter);

    Condition ngt(Object parameter);

    Condition like(String parameter);

    Column as(String alias);

    Condition in(String parameter);
}
