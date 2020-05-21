package io.github.sroca3.scrawl.sqlserver.schema;

import java.util.Optional;

public class AliasedColumn extends SimpleColumn implements Column, Aliasable {
    private final String alias;
    public AliasedColumn(final Column column, String alias) {
        super(column.getName());
        this.alias = alias;
    }

    @Override
    public String getName() {
        return Optional.ofNullable(getAlias())
                       .map(alias -> super.getName() + " AS " + alias)
                       .orElse(super.getName());
    }

    @Override
    public String getAlias() {
        return this.alias;
    }
}
