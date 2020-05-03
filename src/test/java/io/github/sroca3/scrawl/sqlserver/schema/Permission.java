package io.github.sroca3.scrawl.sqlserver.schema;

public class Permission extends AbstractTable<Permission> {
    public static final Permission PERMISSIONS = new Permission();

    private Permission() {
        this("");
    }

    private Permission(String alias) {
        super("IAM", "Permissions", alias);
    }

    public Column name() {
        return new GenericColumn(String.join(".",getAlias(), "Name"));
    }

    @Override
    public Column[] columns() {
        return new Column[] {
            name()
        };
    }

    @Override
    public Permission as(String alias) {
        return new Permission(alias);
    }

}
