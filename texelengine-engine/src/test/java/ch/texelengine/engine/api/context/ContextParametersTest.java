package ch.texelengine.engine.api.context;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextParametersTest {

    @Test
    public void ConstructorWorks() {
        ContextParameters params = new ContextParameters(3, 2, true);
        assertEquals(3, params.major());
        assertEquals(2, params.minor());
        assertEquals(true, params.noErrors());
    }

    @Test
    public void ConstructorDefaultsWorks() {
        ContextParameters params = new ContextParameters(3, 2);
        assertEquals(false, params.noErrors());
    }
}