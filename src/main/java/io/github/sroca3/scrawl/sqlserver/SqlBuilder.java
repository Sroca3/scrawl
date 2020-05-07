package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.AbstractTable;
import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.SimpleColumn;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class SqlBuilder {
    private static final String SPACE = " ";
    private static final String COLON = ":";
    private static final String COMMA_SPACE = ", ";
    private static final String SELECT = "SELECT";
    private static final String FROM = "FROM";
    private static final String WHERE = "WHERE";

    boolean isSelectQuery;
    private List<Column> columns = Collections.emptyList();
    private Table<?> rootTable;
    private String whereClause = "";
    private String orderByClause = "";
    private Parameters parameters = new Parameters();

    public void markAsSelectQuery() {
        this.isSelectQuery = true;
    }

    public String build() {
        var builder = new StringBuilder();
        if (isSelectQuery) {
            builder.append(SELECT);
            appendColumns(builder);
            appendRootTable(builder);
            appendWhereClause(builder);
            appendOrderByClause(builder);
        }
        return builder.toString();
    }

    private void appendOrderByClause(StringBuilder builder) {
        if(isNotBlank(this.orderByClause)) {
            builder.append(this.orderByClause);
        }
    }

    private void appendColumns(StringBuilder builder) {
        if (!columns.isEmpty()) {
            builder.append(SPACE)
                   .append(String.join(COMMA_SPACE, columns.parallelStream().map(Column::getName).collect(toList())));
        }
    }

    private void appendRootTable(StringBuilder builder) {
        if (rootTable != null) {
            builder.append(SPACE)
                   .append(FROM)
                   .append(SPACE)
                   .append(rootTable.getName());
            if (isNotBlank(rootTable.getAlias())) {
                builder.append(SPACE)
                       .append(rootTable.getAlias());
            }
        }
    }

    private void appendWhereClause(StringBuilder builder) {
        if (isNotBlank(whereClause)) {
            builder.append(SPACE)
                   .append(WHERE)
                   .append(SPACE)
                   .append(whereClause);
        }
    }

    private boolean isNotBlank(String string) {
        return string != null && !string.isBlank();
    }

    public void addColumnNames(List<String> columns) {
        this.columns = columns.parallelStream().map(SimpleColumn::new).collect(toList());
    }

    public void addRootTable(String tableName) {
        this.rootTable = new GenericTable(tableName);
    }

    public void addWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public void addRootTable(Table<?> table) {
        this.rootTable = table;
    }

    public void addColumns(List<Column> columns) {
        this.columns = List.copyOf(columns);
    }

    public void addConditionToWhereClause(Condition condition1) {
        condition1.setInitialParameters(this.parameters);
        this.whereClause = condition1.getSql();
        this.parameters = condition1.getParameters();
    }

    public void addOrderByClause(String[] columns) {
        this.orderByClause = " ORDER BY " + String.join(COMMA_SPACE, columns);
    }

    public Map<String, Object> getParameterMap() {
        return parameters.toMap();
    }

    private static class GenericTable extends AbstractTable<GenericTable> {
        public GenericTable(String tableName) {
            super(tableName, "");
        }

        @Override
        public Column[] columns() {
            return new Column[0];
        }

        @Override
        public GenericTable as(String alias) {
            return null;
        }
    }
}
