package io.github.sroca3.scrawl.sqlserver;

import java.util.Map;

public interface TerminatingClause {
    String getSql();

    Map<String, Object> getParameterMap();
}
