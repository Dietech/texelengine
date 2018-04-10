package ch.texelengine.math.linearalgebra;

import static ch.texelengine.testing.TestRandomizer.*;
import static org.junit.Assume.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QuaternionfTest {

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
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            assertEquals(x, q.x());
            assertEquals(y, q.y());
            assertEquals(z, q.z());
            assertEquals(w, q.w());
            assertEquals(x, qCopy.x());
            assertEquals(y, qCopy.y());
            assertEquals(z, qCopy.z());
            assertEquals(w, qCopy.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float angle = rng.nextFloat() * 360;
            Vector3f axis = new Vector3f(x, y, z);
            Quaternionf q = new Quaternionf(axis, angle);
            double hAngle = Math.toRadians(angle) * 0.5;
            float sin = (float)Math.sin(hAngle);
            float cos = (float)Math.cos(hAngle);
            assertEquals(x * sin, q.x());
            assertEquals(y * sin, q.y());
            assertEquals(z * sin, q.z());
            assertEquals(cos, q.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * 360;
            float y = rng.nextFloat() * 360;
            float z = rng.nextFloat() * 360;
            Quaternionf q = new Quaternionf(x, y, z);
            double xAngle = Math.toRadians(x) * 0.5;
            double yAngle = Math.toRadians(y) * 0.5;
            double zAngle = Math.toRadians(z) * 0.5;
            float sx = (float)Math.sin(xAngle);
            float cx = (float)Math.cos(xAngle);
            float sy = (float)Math.sin(yAngle);
            float cy = (float)Math.cos(yAngle);
            float sz = (float)Math.sin(zAngle);
            float cz = (float)Math.cos(zAngle);
            float cycz = cy * cz;
            float sysz = sy * sz;
            float sycz = sy * cz;
            float cysz = cy * sz;
            float xR = sx * cycz + cx * sysz;
            float yR = cx * sycz - sx * cysz;
            float zR = cx * cysz + sx * sycz;
            float wR = cx * cycz - sx * sysz;
            assertEquals(xR, q.x());
            assertEquals(yR, q.y());
            assertEquals(zR, q.z());
            assertEquals(wR, q.w());
        }
        Quaternionf zero = new Quaternionf();
        assertEquals(0, zero.x());
        assertEquals(0, zero.y());
        assertEquals(0, zero.z());
        assertEquals(1, zero.w());
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
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            q = new Quaternionf();
            assertEquals(false, qCopy.equals(q));
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
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            float diff = (float) (rng.nextFloat() * Integer.MAX_VALUE + 1e-6);
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(x, y, z, w);
            Quaternionf qDiff = new Quaternionf(x + diff, y + diff, z + diff, w + diff);
            assertEquals(true, q.equals(qCopy));
            assertEquals(true, q.equals(q));
            assertEquals(false, qDiff.equals(q));
        }
        Quaternionf q2 = new Quaternionf();
        assertEquals(false, q2.equals(null));
        assertEquals(false, q2.equals(new Object()));
        preconditionTest = true;
    }
    
    @Before
    @Test
    public void AssumePrecondition() {
        assumeTrue("The preconditions for the class where not filled", preconditionTest);
    }
    
    @Test
    public void GettersAccessCorrectValues() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            assertEquals(x, q.x());
            assertEquals(y, q.y());
            assertEquals(z, q.z());
            assertEquals(w, q.w());
        }
    }
    
    @Test
    public void SetterSetCorrectValues() {
        Random rng = newRandom();
        Quaternionf q = new Quaternionf();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            q.set(x, y, z, w);
            assertEquals(x, q.x());
            assertEquals(y, q.y());
            assertEquals(z, q.z());
            assertEquals(w, q.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf orig = new Quaternionf(x, y, z, w);
            q.set(orig);
            assertEquals(x, q.x());
            assertEquals(y, q.y());
            assertEquals(z, q.z());
            assertEquals(w, q.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float angle = rng.nextFloat() * Integer.MAX_VALUE;
            q.set(new Vector3f(x, y, z), angle);
            Quaternionf newQ = new Quaternionf(new Vector3f(x, y, z), angle);
            assertEquals(true, q.equals(newQ));
        }
    }
    
    @Test
    public void ConjugateWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            q.conjugate();
            assertEquals(-x, q.x());
            assertEquals(-y, q.y());
            assertEquals(-z, q.z());
            assertEquals(w, q.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf dest = new Quaternionf();
            q.conjugate(dest);
            assertEquals(-x, dest.x());
            assertEquals(-y, dest.y());
            assertEquals(-z, dest.z());
            assertEquals(w, dest.w());
        }
    }
    
    @Test
    public void ConjugateConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            Quaternionf dest = new Quaternionf();
            q.conjugate(dest);
            assertEquals(true, q.equals(qCopy));
        }
    }
    
    @Test
    public void NormalizeWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            q.normalize();
            assertEquals(q.x() * q.x() + q.y() * q.y() + q.z() * q.z() + q.w() * q.w(), 1, 1e-6);
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf dest = new Quaternionf();
            q.normalize(dest);
            assertEquals(dest.x() * dest.x() + dest.y() * dest.y() + dest.z() * dest.z() + dest.w() * dest.w(), 1, 1e-6);
        }
    }
    
    @Test
    public void NormalizeConserveOriginalObject() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            Quaternionf dest = new Quaternionf();
            q.normalize(dest);
            assertEquals(true, q.equals(qCopy));
        }
    }
    
    @Test
    public void DotWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            float x2 = rng.nextFloat() * Integer.MAX_VALUE;
            float y2 = rng.nextFloat() * Integer.MAX_VALUE;
            float z2 = rng.nextFloat() * Integer.MAX_VALUE;
            float w2 = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf q2 = new Quaternionf(x2, y2, z2, w2);
            float dot = q.dot(q2);
            assertEquals(dot, x * x2 + y * y2 + z * z2 + w * w2);
        }
    }
    
    @Test
    public void AddWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float wAdd = rng.nextFloat() * Integer.MAX_VALUE;
            q.add(xAdd, yAdd, zAdd, wAdd);
            assertEquals(x + xAdd, q.x());
            assertEquals(y + yAdd, q.y());
            assertEquals(z + zAdd, q.z());
            assertEquals(w + wAdd, q.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float wAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qAdd = new Quaternionf(xAdd, yAdd, zAdd, wAdd);
            q.add(qAdd);
            assertEquals(x + xAdd, q.x());
            assertEquals(y + yAdd, q.y());
            assertEquals(z + zAdd, q.z());
            assertEquals(w + wAdd, q.w());
        }
        Quaternionf dest = new Quaternionf();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float wAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qAdd = new Quaternionf(xAdd, yAdd, zAdd, wAdd);
            q.add(qAdd, dest);
            assertEquals(x + xAdd, dest.x());
            assertEquals(y + yAdd, dest.y());
            assertEquals(z + zAdd, dest.z());
            assertEquals(w + wAdd, dest.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float wAdd = rng.nextFloat() * Integer.MAX_VALUE;
            q.add(xAdd, yAdd, zAdd, wAdd, dest);
            assertEquals(x + xAdd, dest.x());
            assertEquals(y + yAdd, dest.y());
            assertEquals(z + zAdd, dest.z());
            assertEquals(w + wAdd, dest.w());
        }
    }
    
    @Test
    public void AddConserveOriginalObject() {
        Random rng = newRandom();
        Quaternionf dest = new Quaternionf();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float wAdd = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qAdd = new Quaternionf(xAdd, yAdd, zAdd, wAdd);
            q.add(qAdd, dest);
            assertEquals(true, q.equals(qCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            float xAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float yAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float zAdd = rng.nextFloat() * Integer.MAX_VALUE;
            float wAdd = rng.nextFloat() * Integer.MAX_VALUE;
            q.add(xAdd, yAdd, zAdd, wAdd, dest);
            assertEquals(true, q.equals(qCopy));
        }
    }
    
    @Test
    public void SubWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            float wSub = rng.nextFloat() * Integer.MAX_VALUE;
            q.sub(xSub, ySub, zSub, wSub);
            assertEquals(x - xSub, q.x());
            assertEquals(y - ySub, q.y());
            assertEquals(z - zSub, q.z());
            assertEquals(w - wSub, q.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            float wSub = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qSub = new Quaternionf(xSub, ySub, zSub, wSub);
            q.sub(qSub);
            assertEquals(x - xSub, q.x());
            assertEquals(y - ySub, q.y());
            assertEquals(z - zSub, q.z());
            assertEquals(w - wSub, q.w());
        }
        Quaternionf dest = new Quaternionf();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            float wSub = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qSub = new Quaternionf(xSub, ySub, zSub, wSub);
            q.sub(qSub, dest);
            assertEquals(x - xSub, dest.x());
            assertEquals(y - ySub, dest.y());
            assertEquals(z - zSub, dest.z());
            assertEquals(w - wSub, dest.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            float wSub = rng.nextFloat() * Integer.MAX_VALUE;
            q.sub(xSub, ySub, zSub, wSub, dest);
            assertEquals(x - xSub, dest.x());
            assertEquals(y - ySub, dest.y());
            assertEquals(z - zSub, dest.z());
            assertEquals(w - wSub, dest.w());
        }
    }
    
    @Test
    public void SubConserveOriginalObject() {
        Random rng = newRandom();
        Quaternionf dest = new Quaternionf();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            float wSub = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qSub = new Quaternionf(xSub, ySub, zSub, wSub);
            q.sub(qSub, dest);
            assertEquals(true, q.equals(qCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            float xSub = rng.nextFloat() * Integer.MAX_VALUE;
            float ySub = rng.nextFloat() * Integer.MAX_VALUE;
            float zSub = rng.nextFloat() * Integer.MAX_VALUE;
            float wSub = rng.nextFloat() * Integer.MAX_VALUE;
            q.sub(xSub, ySub, zSub, wSub, dest);
            assertEquals(true, q.equals(qCopy));
        }
    }
    
    @Test
    public void MulWorks() {
        Random rng = newRandom();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            float wMul = rng.nextFloat() * Integer.MAX_VALUE;
            q.mul(xMul, yMul, zMul, wMul);
            assertEquals(w * xMul + x * wMul + y * zMul - z * yMul, q.x());
            assertEquals(w * yMul - x * zMul + y * wMul + z * xMul, q.y());
            assertEquals(w * zMul + x * yMul - y * xMul + z * wMul, q.z());
            assertEquals(w * wMul - x * xMul - y * yMul - z * zMul, q.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            float wMul = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qMul = new Quaternionf(xMul, yMul, zMul, wMul);
            q.mul(qMul);
            assertEquals(w * xMul + x * wMul + y * zMul - z * yMul, q.x());
            assertEquals(w * yMul - x * zMul + y * wMul + z * xMul, q.y());
            assertEquals(w * zMul + x * yMul - y * xMul + z * wMul, q.z());
            assertEquals(w * wMul - x * xMul - y * yMul - z * zMul, q.w());
        }
        Quaternionf dest = new Quaternionf();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            float wMul = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qMul = new Quaternionf(xMul, yMul, zMul, wMul);
            q.mul(qMul, dest);
            assertEquals(w * xMul + x * wMul + y * zMul - z * yMul, dest.x());
            assertEquals(w * yMul - x * zMul + y * wMul + z * xMul, dest.y());
            assertEquals(w * zMul + x * yMul - y * xMul + z * wMul, dest.z());
            assertEquals(w * wMul - x * xMul - y * yMul - z * zMul, dest.w());
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            float wMul = rng.nextFloat() * Integer.MAX_VALUE;
            q.mul(xMul, yMul, zMul, wMul, dest);
            assertEquals(w * xMul + x * wMul + y * zMul - z * yMul, dest.x());
            assertEquals(w * yMul - x * zMul + y * wMul + z * xMul, dest.y());
            assertEquals(w * zMul + x * yMul - y * xMul + z * wMul, dest.z());
            assertEquals(w * wMul - x * xMul - y * yMul - z * zMul, dest.w());
        }
    }
    
    @Test
    public void MulConserveOriginalObject() {
        Random rng = newRandom();
        Quaternionf dest = new Quaternionf();
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            float wMul = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf qMul = new Quaternionf(xMul, yMul, zMul, wMul);
            q.mul(qMul, dest);
            assertEquals(true, q.equals(qCopy));
        }
        for(int i = 0; i < RANDOM_ITERATIONS; i++) {
            float x = rng.nextFloat() * Integer.MAX_VALUE;
            float y = rng.nextFloat() * Integer.MAX_VALUE;
            float z = rng.nextFloat() * Integer.MAX_VALUE;
            float w = rng.nextFloat() * Integer.MAX_VALUE;
            Quaternionf q = new Quaternionf(x, y, z, w);
            Quaternionf qCopy = new Quaternionf(q);
            float xMul = rng.nextFloat() * Integer.MAX_VALUE;
            float yMul = rng.nextFloat() * Integer.MAX_VALUE;
            float zMul = rng.nextFloat() * Integer.MAX_VALUE;
            float wMul = rng.nextFloat() * Integer.MAX_VALUE;
            q.mul(xMul, yMul, zMul, wMul, dest);
            assertEquals(true, q.equals(qCopy));
        }
    }
}
