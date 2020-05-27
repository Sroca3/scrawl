package io.github.sroca3.scrawl.sqlserver.schema;

public class EmployeesTable extends AbstractTable<EmployeesTable> {
    public static final EmployeesTable EMPLOYEES = new EmployeesTable();
    public EmployeesTable() {
        super("Employees");
    }

    public Column id() {
        return super.column("Id");
    }

    public Column name() {
        return super.column("Name");
    }

    public Column departmentId() {
        return super.column("DepartmentId");
    }
}
