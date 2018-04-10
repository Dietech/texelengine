package ch.texelengine.engine.api.buffers;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static ch.texelengine.engine.testing.TestRandomizer.*;

class VertexBufferLayoutTest {

    @Test
    public void StrideIsCorrect() {
        Random rng = newRandom();
        VertexBufferLayout layout = new VertexBufferLayout();
        int nbFloats = Math.abs(rng.nextInt(256) + 1);
        int nbInts = Math.abs(rng.nextInt(256) + 1);
        int nbBytes = Math.abs(rng.nextInt(256) + 1);
        int nbVector2 = Math.abs(rng.nextInt(256) + 1);
        int nbVector3 = Math.abs(rng.nextInt(256) + 1);
        layout.pushFloats(nbFloats);
        layout.pushUnsignedIntegers(nbInts);
        layout.pushBytes(nbBytes);
        layout.pushVector2f(nbVector2);
        layout.pushVector3f(nbVector3);
        int stride = nbFloats * 4 + nbInts * 4 + nbBytes + nbVector2 * 2 * 4 + nbVector3 * 3 * 4;
        assertEquals(stride, layout.stride());
    }

    @Test
    public void PushElementFailsWithNegativeValues() {
        VertexBufferLayout layout = new VertexBufferLayout();
        assertThrows(IllegalArgumentException.class, () -> layout.pushFloats(-4));
        assertThrows(IllegalArgumentException.class, () -> layout.pushFloats(0));
    }
}