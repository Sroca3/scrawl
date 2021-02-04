package io.github.sroca3.scrawl.sqlserver.schema;

public class ${tableName}Table extends AbstractTable<${tableName}Table> {
    public static final ${tableName}Table ${tableName?upper_case} = new ${tableName}Table();

    private ${tableName}Table() {
        super("${tableName}");
    }

    <#list columns as column>
    public Column ${column?lower_case}() {
        return column("${column}");
    }
    </#list>
}