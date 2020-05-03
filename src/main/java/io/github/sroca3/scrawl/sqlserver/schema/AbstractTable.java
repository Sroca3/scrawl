package io.github.sroca3.scrawl.sqlserver.schema;

public abstract class AbstractTable<T extends Table<T>> implements Table<T> {

    protected final String schemaName;
    protected final String tableName;
    protected String alias;

    public AbstractTable(String tableName, String alias) {
        this(null, tableName, alias);
    }

    public AbstractTable(String schemaName, String tableName, String alias) {
        this.schemaName = schemaName;
        this.tableName = tableName;
        this.alias = alias;
    }

    @Override
    public String getName() {
        if (schemaName != null) {
            return String.join(".", schemaName, tableName);
        }
        return tableName;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    protected Column column(String columnName) {
        if (getAlias() != null) {
            return new GenericColumn(String.join(".", getAlias(), columnName));
        }
        return new GenericColumn(columnName);
    }

    public abstract Column[] columns();
}
