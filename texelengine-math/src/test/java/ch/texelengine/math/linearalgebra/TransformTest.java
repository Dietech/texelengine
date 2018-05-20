package ch.texelengine.math.linearalgebra;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static ch.texelengine.testing.TestRandomizer.newRandom;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dorian Ros
 */
class TransformTest {

    @Test
    public void ConstructorIsIdentity() {
        Transform t = new Transform();
        Matrix4f identity = new Matrix4f().identity();
        assertEquals(identity, t.transform());
    }

    @Test
    public void MoveDoesNotModifyTransform() {
        Transform t = new Transform();
        Random rand = newRandom();
        Matrix4f before = new Matrix4f(t.transform()); //Copy of the transform matrix
        t.move(rand.nextFloat() * Integer.MAX_VALUE, rand.nextFloat() * Integer.MAX_VALUE, rand.nextFloat() * Integer.MAX_VALUE);
        assertEquals(before, t.transform());
        t.calculateTransformation();
        assertNotEquals(before, t.transform());
    }

    @Test
    public void ScaleDoesNotModifyTransform() {
        Transform t = new Transform();
        Random rand = newRandom();
        Matrix4f before = new Matrix4f(t.transform()); //Copy of the transform matrix
        t.scale(rand.nextFloat() * Integer.MAX_VALUE, rand.nextFloat() * Integer.MAX_VALUE, rand.nextFloat() * Integer.MAX_VALUE);
        assertEquals(before, t.transform());
        t.calculateTransformation();
        assertNotEquals(before, t.transform());
    }

    @Test
    public void RotateDoesNotModifyTransform() {
        Transform t = new Transform();
        Random rand = newRandom();
        Matrix4f before = new Matrix4f(t.transform()); //Copy of the transform matrix
        t.rotate(new Vector3f(rand.nextFloat() * Integer.MAX_VALUE, rand.nextFloat() * Integer.MAX_VALUE, rand.nextFloat() * Integer.MAX_VALUE), rand.nextFloat() * Integer.MAX_VALUE);
        assertEquals(before, t.transform());
        t.calculateTransformation();
        assertNotEquals(before, t.transform());
    }
}