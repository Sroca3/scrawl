package io.github.sroca3.scrawl.sqlserver.schema;

public class WrappedColumn extends SimpleColumn implements Column {

    public WrappedColumn(final Column column) {
        super(column.getName());
    }

    @Override
    public String getName() {
        return "(" + super.getName() + ")";
    }
}
