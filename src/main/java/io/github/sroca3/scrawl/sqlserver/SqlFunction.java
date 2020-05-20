package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Expression;
import io.github.sroca3.scrawl.sqlserver.schema.FunctionColumn;
import io.github.sroca3.scrawl.sqlserver.schema.SimpleColumn;
import io.github.sroca3.scrawl.sqlserver.schema.WrappedColumn;

public class SqlFunction {

    private SqlFunction() {
    }

    public static Column count(Column column) {
        return new FunctionColumn(SqlServerFunction.COUNT, column);
    }
}
