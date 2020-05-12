package io.github.sroca3.scrawl.sqlserver.schema;

public class Permission extends AbstractTable<Permission> {
    public static final Permission PERMISSIONS = new Permission();

    private Permission() {
        super("IAM","Permissions", null);
    }

    public Column name() {
        return column("Name");
    }
}
