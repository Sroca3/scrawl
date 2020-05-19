package io.github.sroca3.scrawl.sqlserver;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParametersTest {

    Parameters parameters = new Parameters();

    @Test
    public void generateTooManyParameters() {
        var e = assertThrows(IllegalStateException.class, () -> {
            IntStream.rangeClosed(0, 11).forEach((x) ->
                parameters.addParameter(parameters.generateParameterName("parameter"), null)
            );
        });
        assertEquals("Why do you have more than 10 of the same parameter?", e.getMessage());
    }
}
