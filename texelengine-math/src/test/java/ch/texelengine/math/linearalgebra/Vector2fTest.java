package ch.texelengine.math.linearalgebra;

import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import static ch.texelengine.testing.TestRandomizer.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Vector2fTest {

    private static boolean preconditionTest = false;
    
    @BeforeAll
    @Test
    public static void ConstructorSetCorrectValues() {
        preconditionTest = false;
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
            assertEquals(x, vCopy.x());
            assertEquals(y, vCopy.y());
        }
        Vector2f zero = new Vector2f();
        assertEquals(0, zero.x());
        assertEquals(0, zero.y());
        preconditionTest = true;
    }
    
    @BeforeAll
    @Test
    public static void DeepCopyWorks() {
        preconditionTest = false;
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            v = new Vector2f();
            assertEquals(false, vCopy.equals(v));
        }
        preconditionTest = true;
    }
    
    @BeforeAll
    @Test
    public static void EqualsWorks() {
        preconditionTest = false;
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float diff = (float) (rng.nextFloat() * Integer.MAX_VALUE + 1e-6);
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(x, y);
            Vector2f vDiff = new Vector2f(x + diff, y + diff);
            assertEquals(true, v.equals(vCopy));
            assertEquals(true, v.equals(v));
            assertEquals(false, vDiff.equals(v));
        }
        Vector2f v2 = new Vector2f();
        assertEquals(false, v2.equals(null));
        assertEquals(false, v2.equals(new Object()));
        preconditionTest = true;
    }
    
    @Before
    @Test
    public void AssumePrecondition() {
        assumeTrue("The preconditions for the class where not filled", preconditionTest);
    }
    
    @Test
    public void AxisConstantsAreCorrectlySet() {
        assertEquals(1, Vector2f.AXIS_X.x());
        assertEquals(0, Vector2f.AXIS_X.y());
        assertEquals(0, Vector2f.AXIS_Y.x());
        assertEquals(1, Vector2f.AXIS_Y.y());
    }
    
    @Test
    public void GettersAccessCorrectValues() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
        }
    }
    
    @Test
    public void SetterSetCorrectValues() {
        Random rng = newRandom();
        Vector2f v = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            v.set(x, y);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f orig = new Vector2f(x, y);
            v.set(orig);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
        }
    }
    
    @Test
    public void AddWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.add(xAdd, yAdd);
            assertEquals(x + xAdd, v.x());
            assertEquals(y + yAdd, v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vAdd = new Vector2f(xAdd, yAdd);
            v.add(vAdd);
            assertEquals(x + xAdd, v.x());
            assertEquals(y + yAdd, v.y());
        }
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vAdd = new Vector2f(xAdd, yAdd);
            v.add(vAdd, dest);
            assertEquals(x + xAdd, dest.x());
            assertEquals(y + yAdd, dest.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.add(xAdd, yAdd, dest);
            assertEquals(x + xAdd, dest.x());
            assertEquals(y + yAdd, dest.y());
        }
    }
    
    @Test
    public void AddConserveOriginalObject() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vAdd = new Vector2f(xAdd, yAdd);
            v.add(vAdd, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.add(xAdd, yAdd, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void SubWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.sub(xAdd, yAdd);
            assertEquals(x - xAdd, v.x());
            assertEquals(y - yAdd, v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vAdd = new Vector2f(xAdd, yAdd);
            v.sub(vAdd);
            assertEquals(x - xAdd, v.x());
            assertEquals(y - yAdd, v.y());
        }
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vAdd = new Vector2f(xAdd, yAdd);
            v.sub(vAdd, dest);
            assertEquals(x - xAdd, dest.x());
            assertEquals(y - yAdd, dest.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.sub(xAdd, yAdd, dest);
            assertEquals(x - xAdd, dest.x());
            assertEquals(y - yAdd, dest.y());
        }
    }
    
    @Test
    public void SubConserveOriginalObject() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vSub = new Vector2f(xSub, ySub);
            v.sub(vSub, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            v.sub(xSub, ySub, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void DotWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float otherX = rng.nextFloat() * Integer.MAX_VALUE;
            float otherY = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f other = new Vector2f(otherX, otherY);
            float dot = v.dot(other);
            assertEquals(x * otherX + y * otherY, dot);
        }
    }
    
    @Test
    public void LengthWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float length = v.length();
            assertEquals((float)Math.sqrt(x * x + y * y), length);
        }
    }
    
    @Test
    public void LengthSquaredWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float lengthSquared = v.lengthSquared();
            assertEquals(x * x + y * y, lengthSquared);
        }
    }
    
    @Test
    public void NormalizeWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            v.normalize();
            assertEquals(v.lengthSquared(), 1, 1e-6);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f dest = new Vector2f();
            v.normalize(dest);
            assertEquals(dest.lengthSquared(), 1, 1e-6);
        }
    }
    
    @Test
    public void NormalizeConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            Vector2f dest = new Vector2f();
            v.normalize(dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void NormalizeWithSetLengthWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float length = rng.nextFloat() * 10;
            Vector2f v = new Vector2f(x, y);
            v.normalize(length);
            assertEquals(length, v.length(), 1e-6);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float length = rng.nextFloat() * 10;
            Vector2f v = new Vector2f(x, y);
            Vector2f dest = new Vector2f();
            v.normalize(length, dest);
            assertEquals(length, dest.length(), 1e-6);
        }
    }
    
    @Test
    public void NormalizeWithSetLengthConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float length = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            Vector2f dest = new Vector2f();
            v.normalize(length, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void NegateWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            v.negate();
            assertEquals(-x, v.x());
            assertEquals(-y, v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f dest = new Vector2f();
            v.negate(dest);
            assertEquals(-x, dest.x());
            assertEquals(-y, dest.y());
        }
    }
    
    @Test
    public void NegateConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            Vector2f dest = new Vector2f();
            v.negate(dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void ScalarMultiplyWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float scalar = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            v.mul(scalar);
            assertEquals(x * scalar, v.x());
            assertEquals(y * scalar, v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float scalar = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f dest = new Vector2f();
            v.mul(scalar, dest);
            assertEquals(x * scalar, dest.x());
            assertEquals(y * scalar, dest.y());
        }
    }
    
    @Test
    public void ScalarConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float scalar = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            Vector2f dest = new Vector2f();
            v.mul(scalar, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void VectorMultiplyWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            v.mul(xMul, yMul);
            assertEquals(x * xMul, v.x());
            assertEquals(y * yMul, v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vMul = new Vector2f(xMul, yMul);
            v.mul(vMul);
            assertEquals(x * xMul, v.x());
            assertEquals(y * yMul, v.y());
        }
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vMul = new Vector2f(xMul, yMul);
            v.mul(vMul, dest);
            assertEquals(x * xMul, dest.x());
            assertEquals(y * yMul, dest.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            v.mul(xMul, yMul, dest);
            assertEquals(x * xMul, dest.x());
            assertEquals(y * yMul, dest.y());
        }
    }
    
    @Test
    public void VectorMultiplyConserveOriginalObject() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f vMul = new Vector2f(xMul, yMul);
            v.mul(vMul, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            v.mul(xMul, yMul, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void RotateWorksForKnownValues() {
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float angle = i * (360.0f / RANDOM_ITERATIONS);
            Vector2f v = new Vector2f(1, 0);
            v.rotate(angle);
            assertEquals((float)Math.cos(Math.toRadians(angle)), v.x(), 1e-6);
            assertEquals((float)Math.sin(Math.toRadians(angle)), v.y(), 1e-6);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float angle = i * (360.0f / RANDOM_ITERATIONS);
            Vector2f.AXIS_X.rotate(angle, dest);
            assertEquals((float)Math.cos(Math.toRadians(angle)), dest.x(), 1e-6);
            assertEquals((float)Math.sin(Math.toRadians(angle)), dest.y(), 1e-6);
        }
    }
    
    @Test
    public void RotateConserveOriginalObject() {
        Vector2f dest = new Vector2f();
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(rng.nextFloat() * Integer.MAX_VALUE, rng.nextFloat() * Integer.MAX_VALUE);
            Vector2f vCopy = new Vector2f(v);
            v.rotate(angle, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f();
            v.rotate(angle, dest);
            assertEquals(true, v.equals(new Vector2f()));
        }
    }
    
    @Test
    public void LerpWorks() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float factor = rng.nextFloat() * Integer.MAX_VALUE;
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float yLerp = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vLerp = new Vector2f(xLerp, yLerp);
            v.lerp(vLerp, factor);
            assertEquals((x - xLerp) * factor + x, v.x());
            assertEquals((y - yLerp) * factor + y, v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float factor = rng.nextFloat() * Integer.MAX_VALUE;
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float yLerp = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vLerp = new Vector2f(xLerp, yLerp);
            v.lerp(vLerp, factor, dest);
            assertEquals((x - xLerp) * factor + x, dest.x());
            assertEquals((y - yLerp) * factor + y, dest.y());
        }
    }
    
    @Test
    public void LerpConserveOriginalObject() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float factor = rng.nextFloat() * Integer.MAX_VALUE;
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float yLerp = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            Vector2f vLerp = new Vector2f(xLerp, yLerp);
            v.lerp(vLerp, factor, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void MinWorks() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xMin = rng.nextFloat() * Integer.MAX_VALUE;
            float yMin = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vMin = new Vector2f(xMin, yMin);
            v.min(vMin);
            assertEquals((float)Math.min(x, xMin), v.x());
            assertEquals((float)Math.min(y, yMin), v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xMin = rng.nextFloat() * Integer.MAX_VALUE;
            float yMin = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vMin = new Vector2f(xMin, yMin);
            v.min(vMin, dest);
            assertEquals((float)Math.min(x, xMin), dest.x());
            assertEquals((float)Math.min(y, yMin), dest.y());
        }
    }
    
    @Test
    public void MinConserveOriginalObject() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xMin = rng.nextFloat() * Integer.MAX_VALUE;
            float yMin = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            Vector2f vMin = new Vector2f(xMin, yMin);
            v.min(vMin, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void MaxWorks() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xMax = rng.nextFloat() * Integer.MAX_VALUE;
            float yMax = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vMax = new Vector2f(xMax, yMax);
            v.max(vMax);
            assertEquals((float)Math.max(x, xMax), v.x());
            assertEquals((float)Math.max(y, yMax), v.y());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xMax = rng.nextFloat() * Integer.MAX_VALUE;
            float yMax = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vMax = new Vector2f(xMax, yMax);
            v.max(vMax, dest);
            assertEquals((float)Math.max(x, xMax), dest.x());
            assertEquals((float)Math.max(y, yMax), dest.y());
        }
    }
    
    @Test
    public void MaxConserveOriginalObject() {
        Random rng = newRandom();
        Vector2f dest = new Vector2f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float xMax = rng.nextFloat() * Integer.MAX_VALUE;
            float yMax = rng.nextFloat() * Integer.MAX_VALUE;
            Vector2f v = new Vector2f(x, y);
            Vector2f vCopy = new Vector2f(v);
            Vector2f vMax = new Vector2f(xMax, yMax);
            v.max(vMax, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
}

