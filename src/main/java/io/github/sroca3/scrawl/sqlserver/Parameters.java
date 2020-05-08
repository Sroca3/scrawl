package io.github.sroca3.scrawl.sqlserver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Parameters {
    private static final String COLON = ":";
    private final Map<String, Object> parameterMap = new HashMap<>();

    public Map<String, Object> toMap() {
        return Map.copyOf(parameterMap);
    }

    public void addParameters(Parameters parameters) {
        this.parameterMap.putAll(parameters.toMap());
    }

    public void addParameter(String parameter, Object object) {
        this.parameterMap.put(parameter, object);
    }

    public String generateParameterName(String parameterName) {
        String resolvedName = COLON + parameterName;
        var differentiator = IntStream.rangeClosed(1, 10).iterator();
        while (parameterMap.containsKey(resolvedName)) {
            if (!differentiator.hasNext()) {
                throw new IllegalStateException("Why do you have more than 10 of the same parameter?");
            }
            resolvedName = COLON + parameterName + differentiator.next();
        }
        return resolvedName;
    }
}
