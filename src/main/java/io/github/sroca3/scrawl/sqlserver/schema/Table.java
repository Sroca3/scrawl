package io.github.sroca3.scrawl.sqlserver.schema;

public interface Table<T extends Table<T>> extends Aliasable{
    String getName();

    T as(String alias);
}
