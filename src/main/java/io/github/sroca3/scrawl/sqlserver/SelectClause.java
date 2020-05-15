package io.github.sroca3.scrawl.sqlserver;

public interface SelectClause extends TerminatingClause {
    TerminatingClause one();

    SelectColumnsClause star();

    SelectColumnsClause count();
}
