package io.github.sroca3.scrawl.sqlserver.schema;

public class CityTable extends AbstractTable<CityTable> {
    public static final CityTable CITY = new CityTable();

    private CityTable() {
        this(null);
    }

    private CityTable(String alias) {
        super("City", alias);
    }

    public Column name() {
        return column("Name");
    }

    @Override
    public Column[] columns() {
        return new Column[]{
            name()
        };
    }

    @Override
    public CityTable as(String alias) {
        return new CityTable(alias);
    }
}
