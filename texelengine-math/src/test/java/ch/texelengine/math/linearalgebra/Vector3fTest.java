package ch.texelengine.math.linearalgebra;

import static ch.texelengine.testing.TestRandomizer.*;
import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Vector3fTest {
    
    private static boolean preconditionTest = false;
    
    @BeforeAll
    @Test
    public static void ConstructorSetCorrectValues() {
        preconditionTest = false;
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
            assertEquals(z, v.z());
            assertEquals(x, vCopy.x());
            assertEquals(y, vCopy.y());
            assertEquals(z, vCopy.z());
        }
        Vector3f zero = new Vector3f();
        assertEquals(0, zero.x());
        assertEquals(0, zero.y());
        assertEquals(0, zero.z());
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            v = new Vector3f();
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float diff = (float) (rng.nextFloat() * Integer.MAX_VALUE + 1e-6);
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(x, y, z);
            Vector3f vDiff = new Vector3f(x + diff, y + diff, z + diff);
            assertEquals(true, v.equals(vCopy));
            assertEquals(true, v.equals(v));
            assertEquals(false, vDiff.equals(v));
        }
        Vector3f v2 = new Vector3f();
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
        assertEquals(1, Vector3f.AXIS_X.x());
        assertEquals(0, Vector3f.AXIS_X.y());
        assertEquals(0, Vector3f.AXIS_X.z());
        assertEquals(0, Vector3f.AXIS_Y.x());
        assertEquals(1, Vector3f.AXIS_Y.y());
        assertEquals(0, Vector3f.AXIS_Y.z());
        assertEquals(0, Vector3f.AXIS_Z.x());
        assertEquals(0, Vector3f.AXIS_Z.y());
        assertEquals(1, Vector3f.AXIS_Z.z());
    }
    
    @Test
    public void GettersAccessCorrectValues() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
            assertEquals(z, v.z());
        }
    }
    
    @Test
    public void SetterSetCorrectValues() {
        Random rng = newRandom();
        Vector3f v = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            v.set(x, y, z);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
            assertEquals(z, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f orig = new Vector3f(x, y, z);
            v.set(orig);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
            assertEquals(z, v.z());
        }
    }
    
    @Test
    public void AddWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.add(xAdd, yAdd, zAdd);
            assertEquals(x + xAdd, v.x());
            assertEquals(y + yAdd, v.y());
            assertEquals(z + zAdd, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vAdd = new Vector3f(xAdd, yAdd, zAdd);
            v.add(vAdd);
            assertEquals(x + xAdd, v.x());
            assertEquals(y + yAdd, v.y());
            assertEquals(z + zAdd, v.z());
        }
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vAdd = new Vector3f(xAdd, yAdd, zAdd);
            v.add(vAdd, dest);
            assertEquals(x + xAdd, dest.x());
            assertEquals(y + yAdd, dest.y());
            assertEquals(z + zAdd, dest.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.add(xAdd, yAdd, zAdd, dest);
            assertEquals(x + xAdd, dest.x());
            assertEquals(y + yAdd, dest.y());
            assertEquals(z + zAdd, dest.z());
        }
    }
    
    @Test
    public void AddConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vAdd = new Vector3f(xAdd, yAdd, zAdd);
            v.add(vAdd, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.add(xAdd, yAdd, zAdd, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void SubWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.sub(xAdd, yAdd, zAdd);
            assertEquals(x - xAdd, v.x());
            assertEquals(y - yAdd, v.y());
            assertEquals(z - zAdd, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vAdd = new Vector3f(xAdd, yAdd, zAdd);
            v.sub(vAdd);
            assertEquals(x - xAdd, v.x());
            assertEquals(y - yAdd, v.y());
            assertEquals(z - zAdd, v.z());
        }
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vAdd = new Vector3f(xAdd, yAdd, zAdd);
            v.sub(vAdd, dest);
            assertEquals(x - xAdd, dest.x());
            assertEquals(y - yAdd, dest.y());
            assertEquals(z - zAdd, dest.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            v.sub(xAdd, yAdd, zAdd, dest);
            assertEquals(x - xAdd, dest.x());
            assertEquals(y - yAdd, dest.y());
            assertEquals(z - zAdd, dest.z());
        }
    }
    
    @Test
    public void SubConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vSub = new Vector3f(xSub, ySub, zSub);
            v.sub(vSub, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            v.sub(xSub, ySub, zSub, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void DotWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float otherX = rng.nextFloat() * Integer.MAX_VALUE;
            float otherY = rng.nextFloat() * Integer.MAX_VALUE;
            float otherZ = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f other = new Vector3f(otherX, otherY, otherZ);
            float dot = v.dot(other);
            assertEquals(x * otherX + y * otherY + z * otherZ, dot);
        }
    }
    
    @Test
    public void CrossWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xCross = rng.nextFloat() * Integer.MAX_VALUE;
            float yCross = rng.nextFloat() * Integer.MAX_VALUE;
            float zCross = rng.nextFloat() * Integer.MAX_VALUE;
            v.cross(xCross, yCross, zCross);
            assertEquals(y * zCross - z * yCross, v.x());
            assertEquals(z * xCross - x * zCross, v.y());
            assertEquals(x * yCross - y * xCross, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xCross = rng.nextFloat() * Integer.MAX_VALUE;
            float yCross = rng.nextFloat() * Integer.MAX_VALUE;
            float zCross = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vCross = new Vector3f(xCross, yCross, zCross);
            v.cross(vCross);
            assertEquals(y * zCross - z * yCross, v.x());
            assertEquals(z * xCross - x * zCross, v.y());
            assertEquals(x * yCross - y * xCross, v.z());
        }
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xCross = rng.nextFloat() * Integer.MAX_VALUE;
            float yCross = rng.nextFloat() * Integer.MAX_VALUE;
            float zCross = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vCross = new Vector3f(xCross, yCross, zCross);
            v.cross(vCross, dest);
            assertEquals(y * zCross - z * yCross, dest.x());
            assertEquals(z * xCross - x * zCross, dest.y());
            assertEquals(x * yCross - y * xCross, dest.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xCross = rng.nextFloat() * Integer.MAX_VALUE;
            float yCross = rng.nextFloat() * Integer.MAX_VALUE;
            float zCross = rng.nextFloat() * Integer.MAX_VALUE;
            v.cross(xCross, yCross, zCross, dest);
            assertEquals(y * zCross - z * yCross, dest.x());
            assertEquals(z * xCross - x * zCross, dest.y());
            assertEquals(x * yCross - y * xCross, dest.z());
        }
    }
    
    @Test
    public void CrossConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xCross = rng.nextFloat() * Integer.MAX_VALUE;
            float yCross = rng.nextFloat() * Integer.MAX_VALUE;
            float zCross = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vCross = new Vector3f(xCross, yCross, zCross);
            v.cross(vCross, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xCross = rng.nextFloat() * Integer.MAX_VALUE;
            float yCross = rng.nextFloat() * Integer.MAX_VALUE;
            float zCross = rng.nextFloat() * Integer.MAX_VALUE;
            v.cross(xCross, yCross, zCross, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void AbsoluteValueWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            v.absolute();
            assertEquals(Math.abs(x), v.x());
            assertEquals(Math.abs(y), v.y());
            assertEquals(Math.abs(z), v.z());
        }
    }
    
    @Test
    public void AbsoluteValueConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f dest = new Vector3f();
            v.absolute(dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void ReflectWorksForKnown() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = -rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f yAxis = Vector3f.AXIS_Z;
            v.reflect(yAxis);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
            assertEquals(-z + 0.0, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = -rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f dest = new Vector3f();
            Vector3f yAxis = Vector3f.AXIS_Z;
            v.reflect(yAxis, dest);
            assertEquals(x, dest.x());
            assertEquals(y, dest.y());
            assertEquals(-z + 0.0, dest.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = -rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            v.reflect(0, 0, 1);
            assertEquals(x, v.x());
            assertEquals(y, v.y());
            assertEquals(-z + 0.0, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = -rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f dest = new Vector3f();
            v.reflect(0, 0, 1, dest);
            assertEquals(x, dest.x());
            assertEquals(y, dest.y());
            assertEquals(-z + 0.0, dest.z());
        }
    }
    
    @Test
    public void ReflectConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = -rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f dest = new Vector3f();
            Vector3f yAxis = Vector3f.AXIS_Z;
            v.reflect(yAxis, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float y = (rng.nextInt(2) - 1) * rng.nextFloat() * Integer.MAX_VALUE;
            float z = -rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f dest = new Vector3f();
            v.reflect(0, 0, 1, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void LerpWorks() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float factor = rng.nextFloat() * Integer.MAX_VALUE;
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float yLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float zLerp = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vLerp = new Vector3f(xLerp, yLerp, zLerp);
            v.lerp(vLerp, factor);
            assertEquals((x - xLerp) * factor + x, v.x());
            assertEquals((y - yLerp) * factor + y, v.y());
            assertEquals((z - zLerp) * factor + z, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float factor = rng.nextFloat() * Integer.MAX_VALUE;
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float yLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float zLerp = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vLerp = new Vector3f(xLerp, yLerp, zLerp);
            v.lerp(vLerp, factor, dest);
            assertEquals((x - xLerp) * factor + x, dest.x());
            assertEquals((y - yLerp) * factor + y, dest.y());
            assertEquals((z - zLerp) * factor + z, dest.z());
        }
    }
    
    @Test
    public void LerpConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float factor = rng.nextFloat() * Integer.MAX_VALUE;
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float yLerp = rng.nextFloat() * Integer.MAX_VALUE;
            float zLerp = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f vLerp = new Vector3f(xLerp, yLerp, zLerp);
            v.lerp(vLerp, factor, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void LengthWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float length = v.length();
            assertEquals((float)Math.sqrt(x * x + y * y + z * z), length);
        }
    }
    
    @Test
    public void LengthSquaredWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float lengthSquared = v.lengthSquared();
            assertEquals(x * x + y * y + z * z, lengthSquared);
        }
    }
    
    @Test
    public void NormalizeWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            v.normalize();
            assertEquals(v.lengthSquared(), 1, 1e-6);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f dest = new Vector3f();
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f dest = new Vector3f();
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float length = rng.nextFloat() * 10;
            Vector3f v = new Vector3f(x, y, z);
            v.normalize(length);
            assertEquals(length, v.length(), 1e-6);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float length = rng.nextFloat() * 10;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f dest = new Vector3f();
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float length = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f dest = new Vector3f();
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            v.negate();
            assertEquals(-x, v.x());
            assertEquals(-y, v.y());
            assertEquals(-z, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f dest = new Vector3f();
            v.negate(dest);
            assertEquals(-x, dest.x());
            assertEquals(-y, dest.y());
            assertEquals(-z, dest.z());
        }
    }
    
    @Test
    public void NegateConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f dest = new Vector3f();
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float scalar = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            v.mul(scalar);
            assertEquals(x * scalar, v.x());
            assertEquals(y * scalar, v.y());
            assertEquals(z * scalar, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float scalar = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f dest = new Vector3f();
            v.mul(scalar, dest);
            assertEquals(x * scalar, dest.x());
            assertEquals(y * scalar, dest.y());
            assertEquals(z * scalar, dest.z());
        }
    }
    
    @Test
    public void ScalarConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float scalar = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f dest = new Vector3f();
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
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            v.mul(xMul, yMul, zMul);
            assertEquals(x * xMul, v.x());
            assertEquals(y * yMul, v.y());
            assertEquals(z * zMul, v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vMul = new Vector3f(xMul, yMul, zMul);
            v.mul(vMul);
            assertEquals(x * xMul, v.x());
            assertEquals(y * yMul, v.y());
            assertEquals(z * zMul, v.z());
        }
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vMul = new Vector3f(xMul, yMul, zMul);
            v.mul(vMul, dest);
            assertEquals(x * xMul, dest.x());
            assertEquals(y * yMul, dest.y());
            assertEquals(z * zMul, dest.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            v.mul(xMul, yMul, zMul, dest);
            assertEquals(x * xMul, dest.x());
            assertEquals(y * yMul, dest.y());
            assertEquals(z * zMul, dest.z());
        }
    }
    
    @Test
    public void VectorMultiplyConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f vMul = new Vector3f(xMul, yMul, zMul);
            v.mul(vMul, dest);
            assertEquals(true, v.equals(vCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            v.mul(xMul, yMul, zMul, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void VectorRotationWorksForKnownValues() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(0, 1, 0);
            float angle = rng.nextFloat() * 360;
            v.rotate(Vector3f.AXIS_X, angle);
            assertEquals(Math.sin(Math.toRadians(angle)), v.z(), 1e-6);
            assertEquals(Math.cos(Math.toRadians(angle)), v.y(), 1e-6);
            Vector3f v2 = new Vector3f(0, 1, 0);
            angle = rng.nextFloat() * 360;
            v2.rotate(Vector3f.AXIS_Z, angle);
            assertEquals(-Math.sin(Math.toRadians(angle)), v2.x(), 1e-6);
            assertEquals(Math.cos(Math.toRadians(angle)), v2.y(), 1e-6);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f dest = new Vector3f();
            Vector3f v = new Vector3f(0, 1, 0);
            float angle = rng.nextFloat() * 360;
            v.rotate(Vector3f.AXIS_X, angle, dest);
            assertEquals(Math.sin(Math.toRadians(angle)), dest.z(), 1e-6);
            assertEquals(Math.cos(Math.toRadians(angle)), dest.y(), 1e-6);
            Vector3f v2 = new Vector3f(0, 1, 0);
            angle = rng.nextFloat() * 360;
            v2.rotate(Vector3f.AXIS_Z, angle, dest);
            assertEquals(-Math.sin(Math.toRadians(angle)), dest.x(), 1e-6);
            assertEquals(Math.cos(Math.toRadians(angle)), dest.y(), 1e-6);
        }
    }
    
    @Test
    public void VectorRotationConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f dest = new Vector3f();
            Vector3f v = new Vector3f(0, 1, 0);
            Vector3f vCopy = new Vector3f(v);
            float angle = rng.nextFloat() * 360;
            v.rotate(Vector3f.AXIS_X, angle, dest);
            assertEquals(true, v.equals(vCopy));
            Vector3f v2 = new Vector3f(0, 1, 0);
            Vector3f v2Copy = new Vector3f(v2);
            angle = rng.nextFloat() * 360;
            v2.rotate(Vector3f.AXIS_Z, angle, dest);
            assertEquals(true, v2.equals(v2Copy));
        }
    }
    
    @Test
    public void MinWorks() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xMin = rng.nextFloat() * Integer.MAX_VALUE;
            float yMin = rng.nextFloat() * Integer.MAX_VALUE;
            float zMin = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vMin = new Vector3f(xMin, yMin, zMin);
            v.min(vMin);
            assertEquals(Math.min(x, xMin), v.x());
            assertEquals(Math.min(y, yMin), v.y());
            assertEquals(Math.min(z, zMin), v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xMin = rng.nextFloat() * Integer.MAX_VALUE;
            float yMin = rng.nextFloat() * Integer.MAX_VALUE;
            float zMin = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vMin = new Vector3f(xMin, yMin, zMin);
            v.min(vMin, dest);
            assertEquals(Math.min(x, xMin), dest.x());
            assertEquals(Math.min(y, yMin), dest.y());
            assertEquals(Math.min(z, zMin), dest.z());
        }
    }
    
    @Test
    public void MinConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xMin = rng.nextFloat() * Integer.MAX_VALUE;
            float yMin = rng.nextFloat() * Integer.MAX_VALUE;
            float zMin = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f vMin = new Vector3f(xMin, yMin, zMin);
            v.min(vMin, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void MaxWorks() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xMax = rng.nextFloat() * Integer.MAX_VALUE;
            float yMax = rng.nextFloat() * Integer.MAX_VALUE;
            float zMax = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vMax = new Vector3f(xMax, yMax, zMax);
            v.max(vMax);
            assertEquals(Math.max(x, xMax), v.x());
            assertEquals(Math.max(y, yMax), v.y());
            assertEquals(Math.max(z, zMax), v.z());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xMax = rng.nextFloat() * Integer.MAX_VALUE;
            float yMax = rng.nextFloat() * Integer.MAX_VALUE;
            float zMax = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vMax = new Vector3f(xMax, yMax, zMax);
            v.max(vMax, dest);
            assertEquals(Math.max(x, xMax), dest.x());
            assertEquals(Math.max(y, yMax), dest.y());
            assertEquals(Math.max(z, zMax), dest.z());
        }
    }
    
    @Test
    public void MaxConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float xMax = rng.nextFloat() * Integer.MAX_VALUE;
            float yMax = rng.nextFloat() * Integer.MAX_VALUE;
            float zMax = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            Vector3f vMax = new Vector3f(xMax, yMax, zMax);
            v.max(vMax, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }
    
    @Test
    public void RotateOnXWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(0, 1, 0);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateX(angle);
            assertEquals(0, v.x());
            assertEquals(Math.cos(Math.toRadians(angle)), v.y(), 1e-6);
            assertEquals(Math.sin(Math.toRadians(angle)), v.z(), 1e-6);
            assertEquals(true, new Vector3f(1, 0, 0).rotateX(angle).equals(Vector3f.AXIS_X));
        }
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(0, 1, 0);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateX(angle, dest);
            assertEquals(0, dest.x());
            assertEquals(Math.cos(Math.toRadians(angle)), dest.y(), 1e-6);
            assertEquals(Math.sin(Math.toRadians(angle)), dest.z(), 1e-6);
        }
    }

    @Test
    public void RotateOnXConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(0, 1, 0);
            Vector3f vCopy = new Vector3f(v);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateX(angle, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }

    @Test
    public void RotateOnYWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(1, 0, 0);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateY(angle);
            assertEquals(0, v.y());
            assertEquals(Math.cos(Math.toRadians(angle)), v.x(), 1e-6);
            assertEquals(-Math.sin(Math.toRadians(angle)), v.z(), 1e-6);
            assertEquals(true, new Vector3f(0, 1, 0).rotateY(angle).equals(Vector3f.AXIS_Y));
        }
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(1, 0, 0);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateY(angle, dest);
            assertEquals(0, dest.y());
            assertEquals(Math.cos(Math.toRadians(angle)), dest.x(), 1e-6);
            assertEquals(-Math.sin(Math.toRadians(angle)), dest.z(), 1e-6);
        }
    }

    @Test
    public void RotateOnYConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(1, 0, 0);
            Vector3f vCopy = new Vector3f(v);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateY(angle, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }

    @Test
    public void RotateOnZWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(0, 1, 0);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateZ(angle);
            assertEquals(0, v.z());
            assertEquals(Math.cos(Math.toRadians(angle)), v.y(), 1e-6);
            assertEquals(-Math.sin(Math.toRadians(angle)), v.x(), 1e-6);
            assertEquals(true, new Vector3f(0, 0, 1).rotateZ(angle).equals(Vector3f.AXIS_Z));
        }
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(0, 1, 0);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateZ(angle, dest);
            assertEquals(0, dest.z());
            assertEquals(Math.cos(Math.toRadians(angle)), dest.y(), 1e-6);
            assertEquals(-Math.sin(Math.toRadians(angle)), dest.x(), 1e-6);
        }
    }

    @Test
    public void RotateOnZConserveOriginalObject() {
        Random rng = newRandom();
        Vector3f dest = new Vector3f();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            Vector3f v = new Vector3f(0, 1, 0);
            Vector3f vCopy = new Vector3f(v);
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            v.rotateZ(angle, dest);
            assertEquals(true, v.equals(vCopy));
        }
    }

    @Test
    public void TransformByMatrixWorks() {
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
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            v.transform(m);
            float invW = 1.0f / (m03 * x + m13 * y + m23 * z + m33);
            float newX = (m00 * x + m10 * y + m20 * z + m30) * invW;
            float newY = (m01 * x + m11 * y + m21 * z + m31) * invW;
            float newZ = (m02 * x + m12 * y + m22 * z + m32) * invW;
            assertEquals(newX, v.x());
            assertEquals(newY, v.y());
            assertEquals(newZ, v.z());
        }
        Vector3f dest = new Vector3f();
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
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            v.transform(m, dest);
            float invW = 1.0f / (m03 * x + m13 * y + m23 * z + m33);
            float newX = (m00 * x + m10 * y + m20 * z + m30) * invW;
            float newY = (m01 * x + m11 * y + m21 * z + m31) * invW;
            float newZ = (m02 * x + m12 * y + m22 * z + m32) * invW;
            assertEquals(newX, dest.x());
            assertEquals(newY, dest.y());
            assertEquals(newZ, dest.z());
        }
    }

    @Test
    public void TransformByMatrixConserveOriginalObject() {
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
            Matrix4f mCopy = new Matrix4f(m);
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            v.transform(m);
            assertEquals(true, m.equals(mCopy));
        }
        Vector3f dest = new Vector3f();
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
            Matrix4f mCopy = new Matrix4f(m);
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            Vector3f v = new Vector3f(x, y, z);
            Vector3f vCopy = new Vector3f(v);
            v.transform(m, dest);
            float invW = 1.0f / (m03 * x + m13 * y + m23 * z + m33);
            float newX = (m00 * x + m10 * y + m20 * z + m30) * invW;
            float newY = (m01 * x + m11 * y + m21 * z + m31) * invW;
            float newZ = (m02 * x + m12 * y + m22 * z + m32) * invW;
            assertEquals(true, v.equals(vCopy));
            assertEquals(true, m.equals(mCopy));
        }
    }
}
