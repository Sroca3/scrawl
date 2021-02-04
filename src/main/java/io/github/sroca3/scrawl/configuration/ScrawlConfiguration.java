package io.github.sroca3.scrawl.configuration;

import java.util.List;
import java.util.Map;

public class ScrawlConfiguration {
    private NamespacedConfiguration scrawl;

    public NamespacedConfiguration getScrawl() {
        return scrawl;
    }

    public void setScrawl(NamespacedConfiguration scrawl) {
        this.scrawl = scrawl;
    }

    public static class NamespacedConfiguration {
        private Map<String, List<String>> tables;

        public Map<String, List<String>> getTables() {
            return tables;
        }

        public void setTables(Map<String, List<String>> tables) {
            this.tables = tables;
        }
    }
}
