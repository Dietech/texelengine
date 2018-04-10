package ch.texelengine.math.linearalgebra;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static ch.texelengine.testing.TestRandomizer.RANDOM_ITERATIONS;
import static ch.texelengine.testing.TestRandomizer.newRandom;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

class Matrix4fTest {

    private static boolean preconditionTest = false;

    @BeforeAll
    @Test
    public static void ConstructorSetCorrectValues() {
        preconditionTest = false;
        Random rng = newRandom();
        Matrix4f m = new Matrix4f();
        assertEquals(m.m00(), 1);
        assertEquals(m.m11(), 1);
        assertEquals(m.m22(), 1);
        assertEquals(m.m33(), 1);
        assertEquals(m.m01(), 0);
        assertEquals(m.m02(), 0);
        assertEquals(m.m03(), 0);
        assertEquals(m.m10(), 0);
        assertEquals(m.m12(), 0);
        assertEquals(m.m13(), 0);
        assertEquals(m.m20(), 0);
        assertEquals(m.m21(), 0);
        assertEquals(m.m23(), 0);
        assertEquals(m.m30(), 0);
        assertEquals(m.m31(), 0);
        assertEquals(m.m32(), 0);
        for (int i = 0; i < RANDOM_ITERATIONS; i++) {
            float m00 = rng.nextFloat() * Integer.MAX_VALUE;
            float m01 = rng.nextFloat() * Integer.MAX_VALUE;
            float m02 = rng.nextFloat() * Integer.MAX_VALUE;
            float m03 = rng.nextFloat() * Integer.MAX_VALUE;
            float m10 = rng.nextFloat() * Integer.MAX_VALUE;
            float m11 = rng.nextFloat() * Integer.MAX_VALUE;
            float m12 = rng.nextFloat() * Integer.MAX_VALUE;
            float m13 = rng.nextFloat() * Integer.MAX_VALUE;
            float m20 = rng.nextFloat() * Integer.MAX_VALUE;
            float m21 = rng.nextFloat() * Integer.MAX_VALUE;
            float m22 = rng.nextFloat() * Integer.MAX_VALUE;
            float m23 = rng.nextFloat() * Integer.MAX_VALUE;
            float m30 = rng.nextFloat() * Integer.MAX_VALUE;
            float m31 = rng.nextFloat() * Integer.MAX_VALUE;
            float m32 = rng.nextFloat() * Integer.MAX_VALUE;
            float m33 = rng.nextFloat() * Integer.MAX_VALUE;
            Matrix4f m2 = new Matrix4f(m00, m10, m20, m30,
                    m01, m11, m21, m31,
                    m02, m12, m22, m32,
                    m03, m13, m23, m33);
            assertEquals(m2.m00(), m00);
            assertEquals(m2.m01(), m01);
            assertEquals(m2.m02(), m02);
            assertEquals(m2.m03(), m03);
            assertEquals(m2.m10(), m10);
            assertEquals(m2.m11(), m11);
            assertEquals(m2.m12(), m12);
            assertEquals(m2.m13(), m13);
            assertEquals(m2.m20(), m20);
            assertEquals(m2.m21(), m21);
            assertEquals(m2.m22(), m22);
            assertEquals(m2.m23(), m23);
            assertEquals(m2.m30(), m30);
            assertEquals(m2.m31(), m31);
            assertEquals(m2.m32(), m32);
            assertEquals(m2.m33(), m33);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float m00 = rng.nextFloat() * Integer.MAX_VALUE;
            float m01 = rng.nextFloat() * Integer.MAX_VALUE;
            float m02 = rng.nextFloat() * Integer.MAX_VALUE;
            float m03 = rng.nextFloat() * Integer.MAX_VALUE;
            float m10 = rng.nextFloat() * Integer.MAX_VALUE;
            float m11 = rng.nextFloat() * Integer.MAX_VALUE;
            float m12 = rng.nextFloat() * Integer.MAX_VALUE;
            float m13 = rng.nextFloat() * Integer.MAX_VALUE;
            float m20 = rng.nextFloat() * Integer.MAX_VALUE;
            float m21 = rng.nextFloat() * Integer.MAX_VALUE;
            float m22 = rng.nextFloat() * Integer.MAX_VALUE;
            float m23 = rng.nextFloat() * Integer.MAX_VALUE;
            float m30 = rng.nextFloat() * Integer.MAX_VALUE;
            float m31 = rng.nextFloat() * Integer.MAX_VALUE;
            float m32 = rng.nextFloat() * Integer.MAX_VALUE;
            float m33 = rng.nextFloat() * Integer.MAX_VALUE;
            Matrix4f m3 = new Matrix4f(m00, m10, m20, m30,
                    m01, m11, m21, m31,
                    m02, m12, m22, m32,
                    m03, m13, m23, m33);
            Matrix4f copy = new Matrix4f(m3);
            assertEquals(copy.m00(), m00);
            assertEquals(copy.m01(), m01);
            assertEquals(copy.m02(), m02);
            assertEquals(copy.m03(), m03);
            assertEquals(copy.m10(), m10);
            assertEquals(copy.m11(), m11);
            assertEquals(copy.m12(), m12);
            assertEquals(copy.m13(), m13);
            assertEquals(copy.m20(), m20);
            assertEquals(copy.m21(), m21);
            assertEquals(copy.m22(), m22);
            assertEquals(copy.m23(), m23);
            assertEquals(copy.m30(), m30);
            assertEquals(copy.m31(), m31);
            assertEquals(copy.m32(), m32);
            assertEquals(copy.m33(), m33);
        }
        preconditionTest = true;
    }

    @BeforeAll
    @Test
    public static void DeepCopyWorks() {
        preconditionTest = false;
        Random rng = newRandom();
        float m00 = rng.nextFloat() * Integer.MAX_VALUE;
        float m01 = rng.nextFloat() * Integer.MAX_VALUE;
        float m02 = rng.nextFloat() * Integer.MAX_VALUE;
        float m03 = rng.nextFloat() * Integer.MAX_VALUE;
        float m10 = rng.nextFloat() * Integer.MAX_VALUE;
        float m11 = rng.nextFloat() * Integer.MAX_VALUE;
        float m12 = rng.nextFloat() * Integer.MAX_VALUE;
        float m13 = rng.nextFloat() * Integer.MAX_VALUE;
        float m20 = rng.nextFloat() * Integer.MAX_VALUE;
        float m21 = rng.nextFloat() * Integer.MAX_VALUE;
        float m22 = rng.nextFloat() * Integer.MAX_VALUE;
        float m23 = rng.nextFloat() * Integer.MAX_VALUE;
        float m30 = rng.nextFloat() * Integer.MAX_VALUE;
        float m31 = rng.nextFloat() * Integer.MAX_VALUE;
        float m32 = rng.nextFloat() * Integer.MAX_VALUE;
        float m33 = rng.nextFloat() * Integer.MAX_VALUE;
        Matrix4f m3 = new Matrix4f(m00, m10, m20, m30,
                m01, m11, m21, m31,
                m02, m12, m22, m32,
                m03, m13, m23, m33);
        Matrix4f copy = new Matrix4f(m3);
        m3.identity();
        assertEquals(false, copy.equals(m3));
        preconditionTest = true;
    }

    @BeforeAll
    @Test
    public static void EqualsWorks() {
        preconditionTest = false;
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float m00 = rng.nextFloat() * Integer.MAX_VALUE;
            float m01 = rng.nextFloat() * Integer.MAX_VALUE;
            float m02 = rng.nextFloat() * Integer.MAX_VALUE;
            float m03 = rng.nextFloat() * Integer.MAX_VALUE;
            float m10 = rng.nextFloat() * Integer.MAX_VALUE;
            float m11 = rng.nextFloat() * Integer.MAX_VALUE;
            float m12 = rng.nextFloat() * Integer.MAX_VALUE;
            float m13 = rng.nextFloat() * Integer.MAX_VALUE;
            float m20 = rng.nextFloat() * Integer.MAX_VALUE;
            float m21 = rng.nextFloat() * Integer.MAX_VALUE;
            float m22 = rng.nextFloat() * Integer.MAX_VALUE;
            float m23 = rng.nextFloat() * Integer.MAX_VALUE;
            float m30 = rng.nextFloat() * Integer.MAX_VALUE;
            float m31 = rng.nextFloat() * Integer.MAX_VALUE;
            float m32 = rng.nextFloat() * Integer.MAX_VALUE;
            float m33 = rng.nextFloat() * Integer.MAX_VALUE;
            Matrix4f m = new Matrix4f(m00, m10, m20, m30,
                    m01, m11, m21, m31,
                    m02, m12, m22, m32,
                    m03, m13, m23, m33);
            Matrix4f copy = new Matrix4f(m);
            Matrix4f diff = new Matrix4f();
            copy.add(m, diff);
            assertEquals(true, m.equals(copy));
            assertEquals(true, m.equals(m));
            assertEquals(false, diff.equals(m));
        }
        Matrix4f m2 = new Matrix4f();
        assertEquals(false, m2.equals(null));
        assertEquals(false, m2.equals(new Object()));
        preconditionTest = true;
    }

    @Before
    @Test
    public void AssumePrecondition() {
        assumeTrue("The preconditions for the class where not filled", preconditionTest);
    }
}