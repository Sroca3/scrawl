package io.github.sroca3.scrawl.sqlserver;

public enum JoinType {
    INNER("JOIN"),
    LEFT("LEFT JOIN"),
    RIGHT("RIGHT JOIN"),
    OUTER("OUTER JOIN");

    private final String keyword;

    JoinType(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }
}

