package ch.texelengine.math.linearalgebra;

import java.nio.FloatBuffer;

/**
 * Represents a 4 by 4 matrix of <code>float</code> values
 * 
 * <p>
 * Most of the operation on this object modify its values.
 * You therefore need to be careful and copy the object in some cases.
 * Alternatively you can use the copy methods that take another matrix as an output object.
 * </p>
 * 
 * <p>The matrix is column-major order to match OpenGL's interpretation</p>
 * <p><tt>
 * m00 m10 m20 m30<br>
 * m01 m11 m21 m31<br>
 * m02 m12 m22 m32<br>
 * m03 m13 m23 m33<br>
 * </tt></p>
 * 
 * @author Dorian Ros
 *
 */
public final class Matrix4f {

    /**
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @param m00 the value of m00
     * @param m01 the value of m01
     * @param m02 the value of m02
     * @param m03 the value of m03
     */
    private float m00, m01, m02, m03;
    
    /**
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @param m10 the value of m10
     * @param m11 the value of m11
     * @param m12 the value of m12
     * @param m13 the value of m13
     */
    private float m10, m11, m12, m13;
    
    /**
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @param m20 the value of m20
     * @param m21 the value of m21
     * @param m22 the value of m22
     * @param m23 the value of m23
     */
    private float m20, m21, m22, m23;
    
    /**
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @param m30 the value of m30
     * @param m31 the value of m31
     * @param m32 the value of m32
     * @param m33 the value of m33
     */
    private float m30, m31, m32, m33;
    
    /**
     * Create a new {@link Matrix4f} and set it to the identity
     */
    public Matrix4f() {
        this.m00 = this.m11 = this.m22 = this.m33 = 1;
        this.m01 = this.m02 = this.m03 = 0;
        this.m10 = this.m12 = this.m13 = 0;
        this.m20 = this.m21 = this.m23 = 0;
        this.m30 = this.m31 = this.m32 = 0;
    }
    
    /**
     * Create a new {@link Matrix4f} and initialize it with the given values
     * 
     * <p>The matrix layout will be:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     *
     * @param m00 the value of m00
     * @param m10 the value of m10
     * @param m20 the value of m20
     * @param m30 the value of m30
     * @param m01 the value of m01
     * @param m11 the value of m11
     * @param m21 the value of m21
     * @param m31 the value of m31
     * @param m02 the value of m02
     * @param m12 the value of m12
     * @param m22 the value of m22
     * @param m32 the value of m32
     * @param m03 the value of m03
     * @param m13 the value of m13
     * @param m23 the value of m23
     * @param m33 the value of m33
     */
    public Matrix4f(float m00, float m10, float m20, float m30,
                    float m01, float m11, float m21, float m31,
                    float m02, float m12, float m22, float m32,
                    float m03, float m13, float m23, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    /**
     * Create a {@link Matrix4f} and initialize it's values with the values
     * of <code>m</code>
     *
     * @param m matrix to copy the values from
     */
    public Matrix4f(Matrix4f m) {
        this.m00 = m.m00;
        this.m01 = m.m01;
        this.m02 = m.m02;
        this.m03 = m.m03;
        this.m10 = m.m10;
        this.m11 = m.m11;
        this.m12 = m.m12;
        this.m13 = m.m13;
        this.m20 = m.m20;
        this.m21 = m.m21;
        this.m22 = m.m22;
        this.m23 = m.m23;
        this.m30 = m.m30;
        this.m31 = m.m31;
        this.m32 = m.m32;
        this.m33 = m.m33;
    }
    
    /**
     * Get the value of m00
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m00
     */
    public float m00() {
        return m00;
    }
    
    /**
     * Get the value of m10
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m10
     */
    public float m10() {
        return m10;
    }
    
    /**
     * Get the value of m20
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m20
     */
    public float m20() {
        return m20;
    }
    
    /**
     * Get the value of m30
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m30
     */
    public float m30() {
        return m30;
    }
    
    /**
     * Get the value of m01
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m01
     */
    public float m01() {
        return m01;
    }
    
    /**
     * Get the value of m11
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m11
     */
    public float m11() {
        return m11;
    }
    
    /**
     * Get the value of m21
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m21
     */
    public float m21() {
        return m21;
    }
    
    /**
     * Get the value of m31
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m31
     */
    public float m31() {
        return m31;
    }
    
    /**
     * Get the value of m02
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m02
     */
    public float m02() {
        return m02;
    }
    
    /**
     * Get the value of m12
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m12
     */
    public float m12() {
        return m12;
    }
    
    /**
     * Get the value of m22
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m22
     */
    public float m22() {
        return m22;
    }
    
    /**
     * Get the value of m32
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m32
     */
    public float m32() {
        return m32;
    }
    
    /**
     * Get the value of m03
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m03
     */
    public float m03() {
        return m03;
    }
    
    /**
     * Get the value of m13
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m13
     */
    public float m13() {
        return m13;
    }
    
    /**
     * Get the value of m23
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m23
     */
    public float m23() {
        return m23;
    }
    
    /**
     * Get the value of m33
     * 
     * <p>The matrix layout is:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     * 
     * @return the <code>float</code> value of m33
     */
    public float m33() {
        return m33;
    }
    
    /**
     * Set the values of <code>this</code> matrix
     * 
     * <p>The matrix layout will be:</p>
     * <p><tt>
     * m00 m10 m20 m30<br>
     * m01 m11 m21 m31<br>
     * m02 m12 m22 m32<br>
     * m03 m13 m23 m33<br>
     * </tt></p>
     *  
     * @param m00 the value of m00
     * @param m10 the value of m10
     * @param m20 the value of m20
     * @param m30 the value of m30
     * @param m01 the value of m01
     * @param m11 the value of m11
     * @param m21 the value of m21
     * @param m31 the value of m31
     * @param m02 the value of m02
     * @param m12 the value of m12
     * @param m22 the value of m22
     * @param m32 the value of m32
     * @param m03 the value of m03
     * @param m13 the value of m13
     * @param m23 the value of m23
     * @param m33 the value of m33
     * @return this
     */
    public Matrix4f set(float m00, float m10, float m20, float m30,
                        float m01, float m11, float m21, float m31,
                        float m02, float m12, float m22, float m32,
                        float m03, float m13, float m23, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        return this;
    }
    
    /**
     * Copy the values from the matrix <code>m</code> into <code>this</code> matrix
     * 
     * @param m the matrix to copy the values from
     * @return this
     */
    public Matrix4f set(Matrix4f m) {
        this.m00 = m.m00;
        this.m01 = m.m01;
        this.m02 = m.m02;
        this.m03 = m.m03;
        this.m10 = m.m10;
        this.m11 = m.m11;
        this.m12 = m.m12;
        this.m13 = m.m13;
        this.m20 = m.m20;
        this.m21 = m.m21;
        this.m22 = m.m22;
        this.m23 = m.m23;
        this.m30 = m.m30;
        this.m31 = m.m31;
        this.m32 = m.m32;
        this.m33 = m.m33;
        return this;
    }
    
    /**
     * Store <code>this</code> matrix in column-major order in the given {@link FloatBuffer}
     * at the current position and increment the position
     * 
     * @param buffer the buffer to store the data into at the current position
     * @return the filled buffer
     */
    public FloatBuffer store(FloatBuffer buffer) {
        buffer.put(m00);
        buffer.put(m01);
        buffer.put(m02);
        buffer.put(m03);
        buffer.put(m10);
        buffer.put(m11);
        buffer.put(m12);
        buffer.put(m13);
        buffer.put(m20);
        buffer.put(m21);
        buffer.put(m22);
        buffer.put(m23);
        buffer.put(m30);
        buffer.put(m31);
        buffer.put(m32);
        buffer.put(m33);
        return buffer;
    }
    
    /**
     * Set <code>this</code> matrix to the identity
     * 
     * @return this
     */
    public Matrix4f identity() {
        this.m00 = this.m11 = this.m22 = this.m33 = 1;
        this.m01 = this.m02 = this.m03 = 0;
        this.m10 = this.m12 = this.m13 = 0;
        this.m20 = this.m21 = this.m23 = 0;
        this.m30 = this.m31 = this.m32 = 0;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a translation matrix
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * 
     * @param x the offset to translate in x
     * @param y the offset to translate in y
     * @param z the offset to translate in z
     * @return this
     */
    public Matrix4f translation(float x, float y, float z) {
        this.m00 = this.m11 = this.m22 = this.m33 = 1;
        this.m01 = this.m02 = this.m03 = 0;
        this.m10 = this.m12 = this.m13 = 0;
        this.m20 = this.m21 = this.m23 = 0;
        this.m30 = x;
        this.m31 = y;
        this.m32 = z;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a translation matrix
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * 
     * @param v the offset to translate as a {@link Vector3f}
     * @return this
     */
    public Matrix4f translation(Vector3f v) {
        this.m00 = this.m11 = this.m22 = this.m33 = 1;
        this.m01 = this.m02 = this.m03 = 0;
        this.m10 = this.m12 = this.m13 = 0;
        this.m20 = this.m21 = this.m23 = 0;
        this.m30 = v.x();
        this.m31 = v.y();
        this.m32 = v.z();
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a rotation matrix
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * <p>
     * If <code>X</code> is the rotation on the x axis, <code>Y</code> the rotation on the y axis, 
     * <code>Z</code> the rotation on the z axis and <code>v</code> a vector to be rotated then the
     * method corresponds to the multiplication <tt>ZYX * v</tt>. So the order in
     * which the rotation are applied is <code>X</code> then <code>Y</code> then <code>Z</code>
     * </p>
     * 
     * @param angleX the angle of rotation on the x axis in degree
     * @param angleY the angle of rotation on the y axis in degree
     * @param angleZ the angle of rotation on the z axis in degree
     * @return this
     */
    public Matrix4f rotation(float angleX, float angleY, float angleZ) {
        double radX = Math.toRadians(angleX);
        double radY = Math.toRadians(angleY);
        double radZ = Math.toRadians(angleZ);
        float sinX = (float) Math.sin(radX);
        float cosX = (float) Math.cos(radX);
        float sinY = (float) Math.sin(radY);
        float cosY = (float) Math.cos(radY);
        float sinZ = (float) Math.sin(radZ);
        float cosZ = (float) Math.cos(radZ);
        float negSinX = -sinX;
        float negSinY = -sinY;
        float negSinZ = -sinZ;

        //rotation on X
        float newM11 = cosX;
        float newM12 = sinX;
        float newM21 = negSinX;
        float newM22 = cosX;
        //rotation on Y
        float newM00 = cosY;
        float newM01 = newM21 * negSinY;
        float newM02 = newM22 * negSinY;
        this.m20 = sinY;
        this.m21 = newM21 * cosY;
        this.m22 = newM22 * cosY;
        this.m23 = 0;
        //rotation on Z
        this.m00 = newM00 * cosZ;
        this.m01 = newM01 * cosZ + newM11 * sinZ;
        this.m02 = newM02 * cosZ + newM12 * sinZ;
        this.m03 = 0;
        this.m10 = newM00 * negSinZ;
        this.m11 = newM01 * negSinZ + newM11 * cosZ;
        this.m12 = newM02 * negSinZ + newM12 * cosZ;
        this.m13 = 0;
        //set last column to identity
        this.m30 = 0;
        this.m31 = 0;
        this.m32 = 0;
        this.m33 = 1;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a rotation matrix based on an axis and an angle of rotation
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * 
     * @param axis the axis of rotation as a {@link Vector3f}
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Matrix4f rotation(Vector3f axis, float angle) {
        float length = axis.length();
        return rotation(axis.x() / length, axis.y() / length, axis.z() / length, angle);
    }
    
    /**
     * Set <code>this</code> matrix to a rotation matrix based on an axis and an angle of rotation
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * 
     * @param x the x coordinate of the axis of rotation
     * @param y the y coordinate of the axis of rotation
     * @param z the z coordinate of the axis of rotation
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Matrix4f rotation(float x, float y, float z, float angle) {
        double rad = Math.toRadians(angle);
        float sin = (float)Math.sin(rad);
        float cos = (float)Math.cos(rad);
        float invCos = 1.0f - cos;
        float xy = x * y;
        float xz = x * z;
        float yz = y * z;
        this.m00 = cos + x * x * invCos;
        this.m10 = xy * invCos - z * sin;
        this.m20 = xz * invCos + y * sin;
        this.m01 = xy * invCos + z * sin;
        this.m11 = cos + y * y * invCos;
        this.m21 = yz * invCos - x * sin;
        this.m02 = xz * invCos - y * sin;
        this.m12 = yz * invCos + x * sin;
        this.m22 = cos + z * z * invCos;
        this.m30 = this.m31 = this.m32 = 0;
        this.m03 = this.m13 = this.m23 = 0;
        this.m33 = 1;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a rotation matrix based on a {@link Quaternionf} rotation
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * 
     * @param q the rotation quaternion
     * @return this
     */
    public Matrix4f rotation(Quaternionf q) {
        float w2 = q.w() * q.w();
        float x2 = q.x() * q.x();
        float y2 = q.y() * q.y();
        float z2 = q.z() * q.z();
        float zw = q.z() * q.w();
        float xy = q.x() * q.y();
        float xz = q.x() * q.z();
        float yw = q.y() * q.w();
        float yz = q.y() * q.z();
        float xw = q.x() * q.w();
        this.m00 = w2 + x2 - z2 - y2;
        this.m01 = xy + zw + zw + xy;
        this.m02 = xz - yw + xz - yw;
        this.m10 = -zw + xy - zw + xy; 
        this.m11 = y2 - z2 + w2 - x2;
        this.m12 = yz + yz + xw + xw;
        this.m20 = yw + xz + xz + yw; 
        this.m21 = yz + yz - xw - xw;
        this.m22 = z2 - y2 - x2 + w2;
        this.m03 = this.m13 = this.m23 = 0;
        this.m30 = this.m31 = this.m32 = 0;
        this.m33 = 1;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a scale matrix
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * 
     * @param x the offset to scale in x
     * @param y the offset to scale in y
     * @param z the offset to scale in z
     * @return this
     */
    public Matrix4f scale(float x, float y, float z) {
        this.m00 = x;
        this.m11 = y;
        this.m22 = z;
        this.m33 = 1;
        this.m01 = this.m02 = this.m03 = 0;
        this.m10 = this.m12 = this.m13 = 0;
        this.m20 = this.m21 = this.m23 = 0;
        this.m30 = this.m31 = this.m32 = 0;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a scale matrix
     * 
     * <p>
     * The resulting matrix can be multiplied against another transformation
     * to obtain an additional transformation
     * </p>
     * 
     * @param v the offset to scale as a {@link Vector3f}
     * @return this
     */
    public Matrix4f scale(Vector3f v) {
        this.m00 = v.x();
        this.m11 = v.y();
        this.m22 = v.z();
        this.m33 = 1;
        this.m01 = this.m02 = this.m03 = 0;
        this.m10 = this.m12 = this.m13 = 0;
        this.m20 = this.m21 = this.m23 = 0;
        this.m30 = this.m31 = this.m32 = 0;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a translation, rotation and scale transformation
     * 
     * <p>
     * The result is a matrix <tt>T * R * S</tt> where <code>T</code> is a translation given by
     * a {@link Vector3f}, <code>R</code> a rotation given by a {@link Quaternionf} and <code>S</code>
     * a scaling transformation given by a vector.
     * </p>
     * 
     * <p>
     * When transforming a vector with <code>this</code> matrix, the scaling is applied first then the rotation then 
     * the translation.
     * </p>
     * 
     * @param translation the translation as a vector
     * @param rotation the rotation as a quaternion
     * @param scale the scaling as a vector
     * @return this
     */
    public Matrix4f translationRotationScale(Vector3f translation, Quaternionf rotation, Vector3f scale) {
        return translationRotationScale(translation.x(), translation.y(), translation.z(),
                                        rotation.x(), rotation.y(), rotation.z(), rotation.w(),
                                        scale.x(), scale.y(), scale.z());
    }
    
    /**
     * Set <code>this</code> matrix to a translation, rotation and scale transformation
     * 
     * <p>
     * The result is a matrix <tt>T * R * S</tt> where <code>T</code> is a translation given by
     * <tt>(tx, ty, tz)</tt>, <code>R</code> a rotation given by <tt>(rx, ry, rz, rw)</tt> and <code>S</code>
     * a scaling transformation given by <tt>(sx, sy, sz)</tt>.
     * </p>
     * 
     * <p>
     * When transforming a vector with <code>this</code> matrix, the scaling is applied first then the rotation then 
     * the translation.
     * </p>
     * 
     * @param tx the translation on the x axis
     * @param ty the translation on the y axis
     * @param tz the translation on the z axis
     * @param rx the x coordinate of the vector part of the quaternion
     * @param ry the y coordinate of the vector part of the quaternion
     * @param rz the z coordinate of the vector part of the quaternion
     * @param rw the scalar part of the quaternion
     * @param sx the scaling factor in the x axis
     * @param sy the scaling factor in the y axis
     * @param sz the scaling factor in the z axis
     * @return this
     */
    public Matrix4f translationRotationScale(float tx, float ty, float tz, 
                                             float rx, float ry, float rz, float rw, 
                                             float sx, float sy, float sz) {
        float srx = rx + rx;
        float sry = ry + ry;
        float srz = rz + rz;
        float q00 = srx * rx;
        float q11 = sry * ry;
        float q22 = srz * rz;
        float q01 = srx * ry;
        float q02 = srx * rz;
        float q03 = srx * rw;
        float q12 = sry * rz;
        float q13 = sry * rw;
        float q23 = srz * rw;
        this.m00 = sx - (q11 + q22) * sx;
        this.m01 = (q01 + q23) * sx;
        this.m02 = (q02 - q13) * sx;
        this.m10 = (q01 - q23) * sy;
        this.m11 = sy - (q22 + q00) * sy;
        this.m12 = (q12 - q03) * sy;
        this.m20 = (q02 + q13) * sz;
        this.m21 = (q12 - q03) * sz;
        this.m22 = sz - (q11 + q00) * sz;
        this.m30 = tx;
        this.m31 = ty;
        this.m32 = tz;
        this.m03 = this.m13 = this.m23 = 0;
        this.m33 = 1;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to an orthographic projection matrix
     * 
     * @param left the distance from the center to the left frustum edge
     * @param right the distance from the center to the right frustum edge
     * @param bottom the distance from the center to the bottom frustum edge
     * @param top the distance from the center to the top frustum edge
     * @param zNear the near clipping plane distance
     * @param zFar the far clipping plane distance
     * @return this
     */
    public Matrix4f orthographic(float left, float right, float bottom, float top, float zNear, float zFar) {
        this.m00 = 2.0f / (right - left);
        this.m11 = 2.0f / (top - bottom);
        this.m22 = 2.0f / (zNear - zFar);
        this.m30 = (right + left) / (left - right);
        this.m31 = (top + bottom) / (bottom - top);
        this.m32 = (zFar + zNear) / (zNear - zFar);
        this.m33 = 1;
        this.m01 = this.m02 = this.m03 = 0;
        this.m10 = this.m12 = this.m13 = 0;
        this.m20 = this.m21 = this.m23 = 0;
        return this;
    }
    
    /**
     * Set <code>this</code> matrix to a perspective projection matrix
     * 
     * @param vFov the vertical field of view in degree. Must be in the range <tt>]0..PI[</tt>
     * @param aspectRatio the aspect ratio. Must be greater than zero
     * @param zNear the near clipping plane distance
     * @param zFar the far clipping plane distance
     * @return this
     */
    public Matrix4f perspective(float vFov, float aspectRatio, float zNear, float zFar) {
        double rad = Math.toRadians(vFov);
        float h = (float)Math.tan(rad * 0.5);
        this.m00 = 1.0f / (h * aspectRatio);
        this.m11 = 1.0f / h;
        this.m22 = (zFar + zNear) / (zNear - zFar);
        this.m32 = ((zFar + zFar) * zNear) / (zNear - zFar);
        this.m23 = -1;
        this.m10 = this.m20 = this.m30 = 0;
        this.m01 = this.m21 = this.m31 = 0;
        this.m02 = this.m12 = 0;
        this.m03 = this.m13 = this.m33 = 0;
        return this;
    }
    
    /**
     * Change the near and far clipping planes distances of <code>this</code> matrix (only works
     * if <code>this</code> is a perspective projection matrix) and store the result in <code>dest</code>
     * 
     * @see #perspective(float, float, float, float)
     * 
     * @param zNear the new near clipping plane distance
     * @param zFar the new far clipping plane distance
     * @param dest the matrix to store the result into
     * @return the matrix holding the result
     */
    public Matrix4f perspectiveFrustumSlice(float zNear, float zFar, Matrix4f dest) {
        float invOldNear = (this.m23 + this.m22) / m32;
        float invNearFar = 1.0f / (zNear - zFar);
        dest.m00 = this.m00 * invOldNear * zNear;
        dest.m01 = this.m01;
        dest.m02 = this.m02;
        dest.m03 = this.m03;
        dest.m10 = this.m10;
        dest.m11 = this.m11 * invOldNear * zNear;
        dest.m12 = this.m12;
        dest.m13 = this.m13;
        dest.m20 = this.m20;
        dest.m21 = this.m21;
        dest.m22 = (zFar + zNear) * invNearFar;
        dest.m23 = this.m23;
        dest.m30 = this.m30;
        dest.m31 = this.m31;
        dest.m32 = (zFar + zFar) * zNear * invNearFar;
        dest.m33 = this.m33;
        return dest;
    }
    
    /**
     * Transpose <code>this</code> matrix
     * 
     * @return this
     */
    public Matrix4f transpose() {
        float newM01 = this.m10;
        float newM02 = this.m20;
        float newM03 = this.m30;
        float newM10 = this.m01;
        float newM12 = this.m21;
        float newM13 = this.m31;
        float newM20 = this.m02;
        float newM21 = this.m12;
        float newM23 = this.m32;
        float newM30 = this.m03;
        float newM31 = this.m13;
        float newM32 = this.m23;
        this.m01 = newM01;
        this.m02 = newM02;
        this.m03 = newM03;
        this.m10 = newM10;
        this.m12 = newM12;
        this.m13 = newM13;
        this.m20 = newM20;
        this.m21 = newM21;
        this.m23 = newM23;
        this.m30 = newM30;
        this.m31 = newM31;
        this.m32 = newM32;
        return this;
    }
    
    /**
     * Transpose <code>this</code> matrix and store the result 
     * in <code>dest</code>
     * 
     * @param dest the matrix to store the result into
     * @return the matrix holding the result
     */
    public Matrix4f transpose(Matrix4f dest) {
        dest.m00 = this.m00;
        dest.m01 = this.m10;
        dest.m02 = this.m20;
        dest.m03 = this.m30;
        dest.m10 = this.m01;
        dest.m11 = this.m11;
        dest.m12 = this.m21;
        dest.m13 = this.m31;
        dest.m20 = this.m02;
        dest.m21 = this.m12;
        dest.m22 = this.m22;
        dest.m23 = this.m32;
        dest.m30 = this.m03;
        dest.m31 = this.m13;
        dest.m32 = this.m23;
        dest.m33 = this.m33;
        return dest;
    }

    /**
     * Calculate the determinant of <code>this</code> matrix
     *
     * @return the determinant as a <code>float</code>
     */
    public float determinant() {
        return (this.m00 * this.m11 - this.m01 * this.m10) * (this.m22 * this.m33 - this.m23 * this.m32)
                + (this.m02 * this.m10 - this.m00 * this.m12) * (this.m21 * this.m33 - this.m23 * this.m31)
                + (this.m00 * this.m13 - this.m03 * this.m10) * (this.m21 * this.m32 - this.m22 * this.m31)
                + (this.m01 * this.m12 - this.m02 * this.m11) * (this.m20 * this.m33 - this.m23 * this.m30)
                + (this.m03 * this.m11 - this.m01 * this.m13) * (this.m20 * this.m32 - this.m22 * this.m30)
                + (this.m02 * this.m13 - this.m03 * this.m12) * (this.m20 * this.m31 - this.m21 * this.m30);
    }

    /**
     * Inverse <code>this</code> matrix
     *
     * @return this
     */
    public Matrix4f inverse() {
        float a = this.m00 * this.m11 - this.m01 * this.m10;
        float b = this.m00 * this.m12 - this.m02 * this.m10;
        float c = this.m00 * this.m13 - this.m03 * this.m10;
        float d = this.m01 * this.m12 - this.m02 * this.m11;
        float e = this.m01 * this.m13 - this.m03 * this.m11;
        float f = this.m02 * this.m13 - this.m03 * this.m12;
        float g = this.m20 * this.m31 - this.m21 * this.m30;
        float h = this.m20 * this.m32 - this.m22 * this.m30;
        float i = this.m20 * this.m33 - this.m23 * this.m30;
        float j = this.m21 * this.m32 - this.m22 * this.m31;
        float k = this.m21 * this.m33 - this.m23 * this.m31;
        float l = this.m22 * this.m33 - this.m23 * this.m32;
        float det = a * l - b * k + c * j + d * i - e * h + f * g;
        float nm00, nm01, nm02, nm03, nm10, nm11, nm12, nm13, nm20, nm21, nm22, nm23, nm30, nm31, nm32, nm33;
        det = 1.0f / det;
        nm00 = (this.m11 * l - this.m12 * k + this.m13 * j) * det;
        nm01 = (-this.m01 * l + this.m02 * k - this.m03 * j) * det;
        nm02 = (this.m31 * f - this.m32 * e + this.m33 * d) * det;
        nm03 = (-this.m21 * f + this.m22 * e - this.m23 * d) * det;
        nm10 = (-this.m10 * l + this.m12 * i - this.m13 * h) * det;
        nm11 = (this.m00 * l - this.m02 * i + this.m03 * h) * det;
        nm12 = (-this.m30 * f + this.m32 * c - this.m33 * b) * det;
        nm13 = (this.m20 * f - this.m22 * c + this.m23 * b) * det;
        nm20 = (this.m10 * k - this.m11 * i + this.m13 * g) * det;
        nm21 = (-this.m00 * k + this.m01 * i - this.m03 * g) * det;
        nm22 = (this.m30 * e - this.m31 * c + this.m33 * a) * det;
        nm23 = (-this.m20 * e + this.m21 * c - this.m23 * a) * det;
        nm30 = (-this.m10 * j + this.m11 * h - this.m12 * g) * det;
        nm31 = (this.m00 * j - this.m01 * h + this.m02 * g) * det;
        nm32 = (-this.m30 * d + this.m31 * b - this.m32 * a) * det;
        nm33 = (this.m20 * d - this.m21 * b + this.m22 * a) * det;
        this.m00 = nm00;
        this.m01 = nm01;
        this.m02 = nm02;
        this.m03 = nm03;
        this.m10 = nm10;
        this.m11 = nm11;
        this.m12 = nm12;
        this.m13 = nm13;
        this.m20 = nm20;
        this.m21 = nm21;
        this.m22 = nm22;
        this.m23 = nm23;
        this.m30 = nm30;
        this.m31 = nm31;
        this.m32 = nm32;
        this.m33 = nm33;
        return this;
    }

    /**
     * Inverse <code>this</code> matrix and store the result in <code>dest</code>
     *
     * @param dest the matrix to store the result into
     * @return the matrix holding the result
     */
    public Matrix4f inverse(Matrix4f dest) {
        float a = this.m00 * this.m11 - this.m01 * this.m10;
        float b = this.m00 * this.m12 - this.m02 * this.m10;
        float c = this.m00 * this.m13 - this.m03 * this.m10;
        float d = this.m01 * this.m12 - this.m02 * this.m11;
        float e = this.m01 * this.m13 - this.m03 * this.m11;
        float f = this.m02 * this.m13 - this.m03 * this.m12;
        float g = this.m20 * this.m31 - this.m21 * this.m30;
        float h = this.m20 * this.m32 - this.m22 * this.m30;
        float i = this.m20 * this.m33 - this.m23 * this.m30;
        float j = this.m21 * this.m32 - this.m22 * this.m31;
        float k = this.m21 * this.m33 - this.m23 * this.m31;
        float l = this.m22 * this.m33 - this.m23 * this.m32;
        float det = a * l - b * k + c * j + d * i - e * h + f * g;
        det = 1.0f / det;
        dest.m00 = (this.m11 * l - this.m12 * k + this.m13 * j) * det;
        dest.m01 = (-this.m01 * l + this.m02 * k - this.m03 * j) * det;
        dest.m02 = (this.m31 * f - this.m32 * e + this.m33 * d) * det;
        dest.m03 = (-this.m21 * f + this.m22 * e - this.m23 * d) * det;
        dest.m10 = (-this.m10 * l + this.m12 * i - this.m13 * h) * det;
        dest.m11 = (this.m00 * l - this.m02 * i + this.m03 * h) * det;
        dest.m12 = (-this.m30 * f + this.m32 * c - this.m33 * b) * det;
        dest.m13 = (this.m20 * f - this.m22 * c + this.m23 * b) * det;
        dest.m20 = (this.m10 * k - this.m11 * i + this.m13 * g) * det;
        dest.m21 = (-this.m00 * k + this.m01 * i - this.m03 * g) * det;
        dest.m22 = (this.m30 * e - this.m31 * c + this.m33 * a) * det;
        dest.m23 = (-this.m20 * e + this.m21 * c - this.m23 * a) * det;
        dest.m30 = (-this.m10 * j + this.m11 * h - this.m12 * g) * det;
        dest.m31 = (this.m00 * j - this.m01 * h + this.m02 * g) * det;
        dest.m32 = (-this.m30 * d + this.m31 * b - this.m32 * a) * det;
        dest.m33 = (this.m20 * d - this.m21 * b + this.m22 * a) * det;
        return dest;
    }
    
    /**
     * Apply the transformation of <code>this</code> matrix to the {@link Vector3f} <code>v</code>
     * 
     * <p>
     * Corresponds to the multiplication <tt>M * v</tt> if <code>M</code> is <code>this</code> matrix and <code>v</code>
     * the vector to transform. This method also perform perspective division and uses <tt>w = 1</tt> as the fourth component
     * </p>
     * 
     * @param v the vector to transform
     * @return the transformed vector
     */
    public Vector3f transform(Vector3f v) {
        float invW = 1.0f / (this.m03 * v.x() + this.m13 * v.y() + this.m23 * v.z() + this.m33);
        float newX = (this.m00 * v.x() + this.m10 * v.y() + this.m20 * v.z() + this.m30) * invW;
        float newY = (this.m01 * v.x() + this.m11 * v.y() + this.m21 * v.z() + this.m31) * invW;
        float newZ = (this.m02 * v.x() + this.m12 * v.y() + this.m22 * v.z() + this.m32) * invW;
        v.set(newX, newY, newZ);
        return v;
    }
    
    /**
     * Apply the transformation of <code>this</code> matrix to the {@link Vector3f} <code>v</code> and store
     * the result in the vector <code>dest</code>
     * 
     * <p>
     * Corresponds to the multiplication <tt>M * v</tt> if <code>M</code> is <code>this</code> matrix and <code>v</code>
     * the vector to transform. This method also perform perspective division and uses <tt>w = 1</tt> as the fourth component
     * </p>
     * 
     * @param v the vector to transform
     * @param dest the vector to store the result in
     * @return the transformed vector
     */
    public Vector3f transform(Vector3f v, Vector3f dest) {
        float invW = 1.0f / (this.m03 * v.x() + this.m13 * v.y() + this.m23 * v.z() + this.m33);
        float newX = (this.m00 * v.x() + this.m10 * v.y() + this.m20 * v.z() + this.m30) * invW;
        float newY = (this.m01 * v.x() + this.m11 * v.y() + this.m21 * v.z() + this.m31) * invW;
        float newZ = (this.m02 * v.x() + this.m12 * v.y() + this.m22 * v.z() + this.m32) * invW;
        dest.set(newX, newY, newZ);
        return dest;
    }
    
    /**
     * Multiply <code>this</code> matrix with the given matrix
     * 
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>Q</code> is the given matrix,
     * then the operation corresponds to <tt>M * Q</tt>
     * 
     * @param m the matrix to multiply by
     * @return this
     */
    public Matrix4f mul(Matrix4f m) {
        float nM00 = this.m00 * m.m00 + this.m10 * m.m01 + this.m20 * m.m02 + this.m30 * m.m03;
        float nM01 = this.m01 * m.m00 + this.m11 * m.m01 + this.m21 * m.m02 + this.m31 * m.m03;
        float nM02 = this.m02 * m.m00 + this.m12 * m.m01 + this.m22 * m.m02 + this.m32 * m.m03;
        float nM03 = this.m03 * m.m00 + this.m13 * m.m01 + this.m23 * m.m02 + this.m33 * m.m03;
        float nM10 = this.m00 * m.m10 + this.m10 * m.m11 + this.m20 * m.m12 + this.m30 * m.m13;
        float nM11 = this.m01 * m.m10 + this.m11 * m.m11 + this.m21 * m.m12 + this.m31 * m.m13;
        float nM12 = this.m02 * m.m10 + this.m12 * m.m11 + this.m22 * m.m12 + this.m32 * m.m13;
        float nM13 = this.m03 * m.m10 + this.m13 * m.m11 + this.m23 * m.m12 + this.m33 * m.m13;
        float nM20 = this.m00 * m.m20 + this.m10 * m.m21 + this.m20 * m.m22 + this.m30 * m.m23;
        float nM21 = this.m01 * m.m20 + this.m11 * m.m21 + this.m21 * m.m22 + this.m31 * m.m23;
        float nM22 = this.m02 * m.m20 + this.m12 * m.m21 + this.m22 * m.m22 + this.m32 * m.m23;
        float nM23 = this.m03 * m.m20 + this.m13 * m.m21 + this.m23 * m.m22 + this.m33 * m.m23;
        float nM30 = this.m00 * m.m30 + this.m10 * m.m31 + this.m20 * m.m32 + this.m30 * m.m33;
        float nM31 = this.m01 * m.m30 + this.m11 * m.m31 + this.m21 * m.m32 + this.m31 * m.m33;
        float nM32 = this.m02 * m.m30 + this.m12 * m.m31 + this.m22 * m.m32 + this.m32 * m.m33;
        float nM33 = this.m03 * m.m30 + this.m13 * m.m31 + this.m23 * m.m32 + this.m33 * m.m33;
        this.m00 = nM00;
        this.m01 = nM01;
        this.m02 = nM02;
        this.m03 = nM03;
        this.m10 = nM10;
        this.m11 = nM11;
        this.m12 = nM12;
        this.m13 = nM13;
        this.m20 = nM20;
        this.m21 = nM21;
        this.m22 = nM22;
        this.m23 = nM23;
        this.m30 = nM30;
        this.m31 = nM31;
        this.m32 = nM32;
        this.m33 = nM33;
        return this;
    }
    
    /**
     * Multiply <code>this</code> matrix with the given matrix and store the result in <code>dest</code>
     * 
     * <p>
     * If <code>M</code> is <code>this</code> matrix and <code>Q</code> is the given matrix,
     * then the operation corresponds to <tt>M * Q</tt>
     * 
     * @param m the matrix to multiply by
     * @param dest the matrix to store the result into
     * @return the matrix holding the result
     */
    public Matrix4f mul(Matrix4f m, Matrix4f dest) {
        dest.m00 = this.m00 * m.m00 + this.m10 * m.m01 + this.m20 * m.m02 + this.m30 * m.m03;
        dest.m01 = this.m01 * m.m00 + this.m11 * m.m01 + this.m21 * m.m02 + this.m31 * m.m03;
        dest.m02 = this.m02 * m.m00 + this.m12 * m.m01 + this.m22 * m.m02 + this.m32 * m.m03;
        dest.m03 = this.m03 * m.m00 + this.m13 * m.m01 + this.m23 * m.m02 + this.m33 * m.m03;
        dest.m10 = this.m00 * m.m10 + this.m10 * m.m11 + this.m20 * m.m12 + this.m30 * m.m13;
        dest.m11 = this.m01 * m.m10 + this.m11 * m.m11 + this.m21 * m.m12 + this.m31 * m.m13;
        dest.m12 = this.m02 * m.m10 + this.m12 * m.m11 + this.m22 * m.m12 + this.m32 * m.m13;
        dest.m13 = this.m03 * m.m10 + this.m13 * m.m11 + this.m23 * m.m12 + this.m33 * m.m13;
        dest.m20 = this.m00 * m.m20 + this.m10 * m.m21 + this.m20 * m.m22 + this.m30 * m.m23;
        dest.m21 = this.m01 * m.m20 + this.m11 * m.m21 + this.m21 * m.m22 + this.m31 * m.m23;
        dest.m22 = this.m02 * m.m20 + this.m12 * m.m21 + this.m22 * m.m22 + this.m32 * m.m23;
        dest.m23 = this.m03 * m.m20 + this.m13 * m.m21 + this.m23 * m.m22 + this.m33 * m.m23;
        dest.m30 = this.m00 * m.m30 + this.m10 * m.m31 + this.m20 * m.m32 + this.m30 * m.m33;
        dest.m31 = this.m01 * m.m30 + this.m11 * m.m31 + this.m21 * m.m32 + this.m31 * m.m33;
        dest.m32 = this.m02 * m.m30 + this.m12 * m.m31 + this.m22 * m.m32 + this.m32 * m.m33;
        dest.m33 = this.m03 * m.m30 + this.m13 * m.m31 + this.m23 * m.m32 + this.m33 * m.m33;
        return this;
    }
    
    /**
     * Add the given matrix to <code>this</code> matrix
     * 
     * @param m the matrix to add
     * @return this
     */
    public Matrix4f add(Matrix4f m) {
        this.m00 += m.m00;
        this.m10 += m.m10;
        this.m20 += m.m20;
        this.m30 += m.m30;
        this.m01 += m.m01;
        this.m11 += m.m11;
        this.m21 += m.m21;
        this.m31 += m.m31;
        this.m02 += m.m02;
        this.m12 += m.m12;
        this.m22 += m.m22;
        this.m32 += m.m32;
        this.m03 += m.m03;
        this.m13 += m.m13;
        this.m23 += m.m23;
        this.m33 += m.m33;
        return this;
    }
    
    /**
     * Add the given matrix to <code>this</code> matrix and store the result in
     * <code>dest</code>
     * 
     * @param m the matrix to add
     * @param dest the matrix to store the result into
     * @return the matrix holding the result
     */
    public Matrix4f add(Matrix4f m, Matrix4f dest) {
        dest.m00 = this.m00 + m.m00;
        dest.m10 = this.m10 + m.m10;
        dest.m20 = this.m20 + m.m20;
        dest.m30 = this.m30 + m.m30;
        dest.m01 = this.m01 + m.m01;
        dest.m11 = this.m11 + m.m11;
        dest.m21 = this.m21 + m.m21;
        dest.m31 = this.m31 + m.m31;
        dest.m02 = this.m02 + m.m02;
        dest.m12 = this.m12 + m.m12;
        dest.m22 = this.m22 + m.m22;
        dest.m32 = this.m32 + m.m32;
        dest.m03 = this.m03 + m.m03;
        dest.m13 = this.m13 + m.m13;
        dest.m23 = this.m23 + m.m23;
        dest.m33 = this.m33 + m.m33;
        return dest;
    }

    /**
     * Subtract the given matrix from <code>this</code> matrix
     *
     * @param m the matrix to subtract from
     * @return this
     */
    public Matrix4f sub(Matrix4f m) {
        this.m00 -= m.m00;
        this.m10 -= m.m10;
        this.m20 -= m.m20;
        this.m30 -= m.m30;
        this.m01 -= m.m01;
        this.m11 -= m.m11;
        this.m21 -= m.m21;
        this.m31 -= m.m31;
        this.m02 -= m.m02;
        this.m12 -= m.m12;
        this.m22 -= m.m22;
        this.m32 -= m.m32;
        this.m03 -= m.m03;
        this.m13 -= m.m13;
        this.m23 -= m.m23;
        this.m33 -= m.m33;
        return this;
    }

    /**
     * Add the given matrix to <code>this</code> matrix and store the result in
     * <code>dest</code>
     *
     * @param m the matrix to add
     * @param dest the matrix to store the result into
     * @return the matrix holding the result
     */
    public Matrix4f sub(Matrix4f m, Matrix4f dest) {
        dest.m00 = this.m00 - m.m00;
        dest.m10 = this.m10 - m.m10;
        dest.m20 = this.m20 - m.m20;
        dest.m30 = this.m30 - m.m30;
        dest.m01 = this.m01 - m.m01;
        dest.m11 = this.m11 - m.m11;
        dest.m21 = this.m21 - m.m21;
        dest.m31 = this.m31 - m.m31;
        dest.m02 = this.m02 - m.m02;
        dest.m12 = this.m12 - m.m12;
        dest.m22 = this.m22 - m.m22;
        dest.m32 = this.m32 - m.m32;
        dest.m03 = this.m03 - m.m03;
        dest.m13 = this.m13 - m.m13;
        dest.m23 = this.m23 - m.m23;
        dest.m33 = this.m33 - m.m33;
        return dest;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Matrix4f) {
            Matrix4f other = (Matrix4f) obj;
            return this.m00 == other.m00 && this.m01 == other.m01 && this.m02 == other.m02 && this.m03 == other.m03 &&
                    this.m10 == other.m10 && this.m11 == other.m11 && this.m12 == other.m12 && this.m13 == other.m13 &&
                    this.m20 == other.m20 && this.m21 == other.m21 && this.m22 == other.m22 && this.m23 == other.m23 &&
                    this.m30 == other.m30 && this.m31 == other.m31 && this.m32 == other.m32 && this.m33 == other.m33;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[(" + this.m00 + " " + this.m10 + " " + this.m20 + " " + this.m30 + ")]\n" +
               "[(" + this.m01 + " " + this.m11 + " " + this.m21 + " " + this.m31 + ")]\n" +
               "[(" + this.m02 + " " + this.m12 + " " + this.m22 + " " + this.m32 + ")]\n" +
               "[(" + this.m03 + " " + this.m13 + " " + this.m23 + " " + this.m33 + ")]";
    }
}
