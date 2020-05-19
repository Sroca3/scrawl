package io.github.sroca3.scrawl.sqlserver;

import io.github.sroca3.scrawl.sqlserver.schema.AbstractTable;
import io.github.sroca3.scrawl.sqlserver.schema.AliasConstructionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AliasTest {

    @Test
    public void constructAliasWithInvalidClass() {
        var e = assertThrows(AliasConstructionException.class, () -> new Unaliasable("").as("x"));
        assertEquals("Could not alias class due to missing no-args constructor.", e.getMessage());
    }

    private static class Unaliasable extends AbstractTable<Unaliasable> {

        public Unaliasable(String tableName) {
            super(tableName);
        }
    }
}
