package io.github.sroca3.scrawl.sqlserver.schema;

public class City extends AbstractTable<City> {
    public static final City CITY = new City();

    private City() {
        this(null);
    }

    private City(String alias) {
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
    public City as(String alias) {
        return new City(alias);
    }
}
