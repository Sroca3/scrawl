package io.github.sroca3.scrawl.sqlserver.schema;

public class UserTable extends AbstractTable<UserTable> {
    public static final UserTable USER = new UserTable();

    private UserTable() {
        super("IAM","User", null);
    }

    public Column username() {
        return column("Username");
    }

    public Column roleId() {
        return column("RoleId");
    }
}
