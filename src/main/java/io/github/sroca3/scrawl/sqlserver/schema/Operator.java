package io.github.sroca3.scrawl.sqlserver.schema;

public enum Operator {
    EQ("="),
    NEQ("<>"),
    NEQ1("!="),
    LT("<"),
    GT(">"),
    LTE("<="),
    GTE(">="),
    NLT("!<"),
    NGT("!>"),
    LIKE("LIKE"),
    IN("IN"),
    NOT_IN("NOT IN");
    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
