package io.github.sroca3.scrawl.sqlserver;

import java.util.Collections;
import java.util.Map;

public interface TerminatingClause {
    String getSql();

    default Map<String, Object> getParameterMap() {
        return Collections.emptyMap();
    }
}
