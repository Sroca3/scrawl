package io.github.sroca3.scrawl.sqlserver.schema;

public class DepartmentsTable extends AbstractTable<DepartmentsTable> {
    public static final DepartmentsTable DEPARTMENTS = new DepartmentsTable();

    public DepartmentsTable() {
        super("Employees");
    }

    public Column id() {
        return super.column("Id");
    }

    public Column name() {
        return super.column("Name");
    }
}
