package io.github.sroca3.scrawl.sqlserver.schema;

public class AliasConstructionException extends RuntimeException {
    public AliasConstructionException() {
        super("Could not alias class due to missing no-args constructor.");
    }
}
