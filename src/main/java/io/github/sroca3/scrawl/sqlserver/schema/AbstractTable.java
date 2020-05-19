package io.github.sroca3.scrawl.sqlserver.schema;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractTable<T extends AbstractTable<T>> implements Table<T> {

    protected final String schemaName;
    protected final String tableName;
    protected String alias;
    protected List<Column> columns = new LinkedList<>();

    public AbstractTable(String tableName) {
        this(tableName, null);
    }

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
        Column column;
        if (getAlias() != null) {
            column = new SimpleColumn(String.join(".", getAlias(), columnName));
        } else {
            column = new SimpleColumn(columnName);
        }
        columns.add(column);
        return column;
    }

    public Column[] columns() {
        return columns.toArray(new Column[0]);
    }

    @Override
    public T as(String alias) {
        try {
            var constructor = getSubClass().getDeclaredConstructor();
            constructor.setAccessible(true);
            var c = constructor.newInstance();
            c.alias = alias;
            return c;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new AliasConstructionException();
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> getSubClass() {
        return (Class<T>) getClass();
    }
}
