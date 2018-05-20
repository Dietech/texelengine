package ch.texelengine.engine.scenegraph.nodes;

import ch.texelengine.math.linearalgebra.Matrix4f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dorian Ros
 */
class SpatialTest {

    @Test
    public void ConstructorInitializesAtOrigin() {
        Spatial node = new Spatial();
        Matrix4f origin = new Matrix4f().identity();
        assertEquals(origin, node.localTransformation());
    }
}