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

    @Test
    public void EqualsWorks() {
        ContextParameters param1 = new ContextParameters(3, 2, true);
        ContextParameters param2 = new ContextParameters(3, 2, true);
        ContextParameters param3 = new ContextParameters(2, 2, false);
        assertEquals(true, param1.equals(param1));
        assertEquals(true, param2.equals(param1));
        assertEquals(false, param1.equals(null));
        assertEquals(false, param1.equals(new Object()));
        assertEquals(false, param1.equals(param3));
    }
}