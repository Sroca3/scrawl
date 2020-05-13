package io.github.sroca3.scrawl.sqlserver.schema;

public class RoleTable extends AbstractTable<RoleTable> {
    public static final RoleTable ROLE = new RoleTable();

    private RoleTable() {
        super("IAM","Role", null);
    }

    public Column id() {
        return column("Id");
    }

    public Column name() {
        return column("Name");
    }
}
