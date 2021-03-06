package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.AbstractTable;
import io.github.sroca3.scrawl.sqlserver.schema.Column;
import io.github.sroca3.scrawl.sqlserver.schema.Condition;
import io.github.sroca3.scrawl.sqlserver.schema.SimpleColumn;
import io.github.sroca3.scrawl.sqlserver.schema.Table;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SqlBuilder {
    private static final String SPACE = " ";
    private static final String COMMA_SPACE = ", ";
    private static final String SELECT = "SELECT";
    private static final String FROM = "FROM";
    private static final String WHERE = "WHERE";
    private static final String ON = "ON";

    private List<Column> columns = Collections.emptyList();
    private Table<?> rootTable;
    private String whereClause = "";
    private String orderByClause = "";
    private Parameters parameters = new Parameters();
    private String joinClause = "";
    private String groupByClause = "";
    private String havingClause = "";

    public String build() {
        var builder = new StringBuilder();
        builder.append(SELECT);
        appendColumns(builder);
        appendRootTable(builder);
        appendJoinClause(builder);
        appendWhereClause(builder);
        appendGroupByClause(builder);
        appendHavingClause(builder);
        appendOrderByClause(builder);
        return builder.toString();
    }

    private void appendHavingClause(StringBuilder builder) {
        builder.append(this.havingClause);
    }

    private void appendGroupByClause(StringBuilder builder) {
        if (isNotBlank(this.groupByClause)) {
            builder.append(this.groupByClause);
        }
    }

    private void appendJoinClause(StringBuilder builder) {
        if (isNotBlank(this.joinClause)) {
            builder.append(joinClause);
        }
    }

    private void appendOrderByClause(StringBuilder builder) {
        if (isNotBlank(this.orderByClause)) {
            builder.append(this.orderByClause);
        }
    }

    private void appendColumns(StringBuilder builder) {
        builder.append(SPACE)
               .append(columns.parallelStream().map(Column::getName).collect(Collectors.joining(COMMA_SPACE)));

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

    public void addRootTable(Table<?> table) {
        this.rootTable = table;
    }

    public void addColumns(List<Column> columns) {
        this.columns = List.copyOf(columns);
    }

    public void addConditionToWhereClause(Condition condition) {
        condition.setInitialParameters(this.parameters);
        this.whereClause = condition.getSql();
        this.parameters = condition.getParameters();
    }

    public void addOrderByClause(String[] columns) {
        this.orderByClause = " ORDER BY " + String.join(COMMA_SPACE, columns);
    }

    public Map<String, Object> getParameterMap() {
        return parameters.toMap();
    }

    public void addJoinClause(JoinType joinType, Table<?> table) {
        this.joinClause = this.joinClause + SPACE + joinType.getKeyword() + SPACE + table.getName();
    }

    public void addJoinCondition(Condition condition) {
        this.joinClause = this.joinClause + SPACE + ON + SPACE + condition.getSql();
    }

    public void addGroupByClause(Column column) {
        this.groupByClause = " GROUP BY " + column.getName();
    }

    public void addHavingClause(Condition condition) {
        this.havingClause = " HAVING " + condition.getSql();
    }

    public void addOrderByClause(Column[] columns) {
        this.addOrderByClause(Arrays.stream(columns)
                                    .map(column -> column.getName())
                                    .collect(Collectors.toList())
                                    .toArray(new String[0]));
    }

    private static class GenericTable extends AbstractTable<GenericTable> {
        public GenericTable(String tableName) {
            super(tableName);
        }
    }
}
