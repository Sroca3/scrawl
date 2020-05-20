package io.github.sroca3.scrawl.sqlserver.schema;

import io.github.sroca3.scrawl.sqlserver.SqlServerFunction;

public class FunctionColumn extends WrappedColumn implements Column {

    private final SqlServerFunction function;

    public FunctionColumn(final SqlServerFunction function, final Column column) {
        super(column);
        this.function = function;
    }

    @Override
    public String getName() {
        return function.name() + super.getName();
    }
}
