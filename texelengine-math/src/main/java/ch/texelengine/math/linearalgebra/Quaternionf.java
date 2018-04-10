package ch.texelengine.math.linearalgebra;

/**
 * Represents a quaternion of <code>float</code> values
 * 
 * <p>
 * Most of the operation on this object modify its values.
 * You therefore need to be careful and copy the object in some cases.
 * Alternatively you can use the copy methods that take another quaternion as an output object.
 * </p>
 * 
 * @author Dorian Ros
 *
 */
public final class Quaternionf {

    /**
     * The x <code>float</code> component of the quaternion
     */
    private float x;
    
    /**
     * The y <code>float</code> component of the quaternion
     */
    private float y;
    
    /**
     * The z <code>float</code> component of the quaternion
     */
    private float z;
    
    /**
     * The w <code>float</code> component of the quaternion
     */
    private float w;
    
    /**
     * Construct a new {@link Quaternionf}
     * 
     * @param x <code>float</code> component
     * @param y <code>float</code> component
     * @param z <code>float</code> component
     * @param w <code>float</code> component
     */
    public Quaternionf(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    /**
     * Constructs a new {@link Quaternionf} with each the vector components <tt>(x, y, z)</tt>
     * set to 0 and the real part <code>w</code> set to 1
     */
    public Quaternionf() {
        this(0, 0, 0, 1);
    }
    
    /**
     * Construct a new {@link Quaternionf} and copy the component values from
     * the quaternion <code>q</code>
     * 
     * @param q the quaternion to copy the values from
     */
    public Quaternionf(Quaternionf q) {
        this(q.x(), q.y(), q.z(), q.w());
    }
    
    /**
     * Construct a new {@link Quaternionf} that represent a rotation by a given
     * angle around an axis <code>c</code> given by a {@link Vector3f}
     * 
     * @param v the axis of the rotation
     * @param angle the angle of rotation in degree
     */
    public Quaternionf(Vector3f v, float angle) {
        double hAngle = Math.toRadians(angle) * 0.5;
        float sin = (float)Math.sin(hAngle);
        float cos = (float)Math.cos(hAngle);
        this.x = v.x() * sin;
        this.y = v.y() * sin;
        this.z = v.z() * sin;
        this.w = cos;
    }
    
    /**
     * Construct a new {@link Quaternionf} that represent a rotation defined
     * by the given Euler angles
     * 
     * @param angleX the angle of rotation about the x axis in degree
     * @param angleY the angle of rotation about the y axis in degree
     * @param angleZ the angle of rotation about the z axis in degree
     */
    public Quaternionf(float angleX, float angleY, float angleZ) {
        double hxRad = Math.toRadians(angleX) * 0.5;
        double hyRad = Math.toRadians(angleY) * 0.5;
        double hzRad = Math.toRadians(angleZ) * 0.5;
        float sx = (float)Math.sin(hxRad);
        float cx = (float)Math.cos(hxRad);
        float sy = (float)Math.sin(hyRad);
        float cy = (float)Math.cos(hyRad);
        float sz = (float)Math.sin(hzRad);
        float cz = (float)Math.cos(hzRad);
        float cycz = cy * cz;
        float sysz = sy * sz;
        float sycz = sy * cz;
        float cysz = cy * sz;
        this.x = sx * cycz + cx * sysz;
        this.y = cx * sycz - sx * cysz;
        this.z = cx * cysz + sx * sycz;
        this.w = cx * cycz - sx * sysz;
    }
    
    /**
     * Get the value of the first component of the vector part
     * of <code>this</code> quaternion
     * 
     * @return the x component as a <code>float</code>
     */
    public float x() {
        return x;
    }
    
    /**
     * Get the value of the second component of the vector part
     * of <code>this</code> quaternion
     * 
     * @return the y component as a <code>float</code>
     */
    public float y() {
        return y;
    }
    
    /**
     * Get the value of the third component of the vector part
     * of <code>this</code> quaternion
     * 
     * @return the z component as a <code>float</code>
     */
    public float z() {
        return z;
    }
    
    /**
     * Get the value of the real part of <code>this</code> quaternion
     * 
     * @return the w component as a <code>float</code>
     */
    public float w() {
        return w;
    }
    
    /**
     * Set the component of <code>this</code> quaternion to the given values
     * 
     * @param x component of the vector part
     * @param y component of the vector part
     * @param z component of the vector part
     * @param w the real part
     * @return this
     */
    public Quaternionf set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }
    
    /**
     * Set the component of <code>this</code> quaternion to be a copy of the given
     * quaternion <code>q</code>
     * 
     * @param q the quaterion to copy
     * @return this
     */
    public Quaternionf set(Quaternionf q) {
        this.x = q.x();
        this.y = q.y();
        this.z = q.z();
        this.w = q.w();
        return this;
    }
    
    /**
     * Set <code>this</code> quaternion to a rotation by a given angle around
     * an axis given by a {@link Vector3f}
     * 
     * @param axis the axis of rotation
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Quaternionf set(Vector3f axis, float angle) {
        double hAngle = Math.toRadians(angle) * 0.5;
        float sin = (float)Math.sin(hAngle);
        float cos = (float)Math.cos(hAngle);
        this.x = axis.x() * sin;
        this.y = axis.y() * sin;
        this.z = axis.z() * sin;
        this.w = cos;
        return this;
    }
    
    /**
     * Conjugate <code>this</code> quaternion
     * 
     * @return this
     */
    public Quaternionf conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }
    
    /**
     * Conjugate th<code>this</code>is quaternion and store the result in the quaternion <code>dest</code>
     * 
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf conjugate(Quaternionf dest) {
        dest.x = -this.x;
        dest.y = -this.y;
        dest.z = -this.z;
        dest.w = this.w;
        return dest;
    }
    
    /**
     * Normalize <code>this</code> quaternion
     * 
     * @return this
     */
    public Quaternionf normalize() {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w)));
        this.x *= invLength;
        this.y *= invLength;
        this.z *= invLength;
        this.w *= invLength;
        return this;
    }
    
    /**
     * Normalize <code>this</code> quaternion and store the result in the quaternion <code>dest</code>
     * 
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf normalize(Quaternionf dest) {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z) + (this.w * this.w)));
        dest.x = this.x * invLength;
        dest.y = this.y * invLength;
        dest.z = this.z * invLength;
        dest.w = this.w * invLength;
        return dest;
    }
    
    /**
     * Return the dot product of <code>this</code> quaternion and the quaternion <code>q</code>
     * 
     * @param q the other quaternion
     * @return the dot product
     */
    public float dot(Quaternionf q) {
        return this.x * q.x() + this.y * q.y() + this.z * q.z() + this.w * q.w();
    }
    
    /**
     * Add the quaternion <code>q</code> to <code>this</code> quaternion
     * 
     * @param q the quaternion to add
     * @return this
     */
    public Quaternionf add(Quaternionf q) {
        this.x += q.x();
        this.y += q.y();
        this.z += q.z();
        this.w += q.w();
        return this;
    }
    
    /**
     * Add the quaternion <code>q</code> to <code>this</code> quaternion and store
     * the result in the quaternion <code>dest</code>
     * 
     * @param q the quaternion to add
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf add(Quaternionf q, Quaternionf dest) {
        dest.x = this.x + q.x();
        dest.y = this.y + q.y();
        dest.z = this.z + q.z();
        dest.w = this.w + q.w();
        return dest;
    }
    
    /**
     * Add the quaternion <tt>(x, y, z, w)</tt> to <code>this</code> quaternion
     * 
     * @param x component of the vector part
     * @param y component of the vector part
     * @param z component of the vector part
     * @param w the real part
     * @return this
     */
    public Quaternionf add(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }
    
    /**
     * Add the quaternion <tt>(x, y, z, w)</tt> to <code>this</code> quaternion and
     * store the result in the quaternion <code>dest</code>
     * 
     * @param x component of the vector part
     * @param y component of the vector part
     * @param z component of the vector part
     * @param w the real part
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf add(float x, float y, float z, float w, Quaternionf dest) {
        dest.x = this.x + x;
        dest.y = this.y + y;
        dest.z = this.z + z;
        dest.w = this.w + w;
        return dest;
    }
    
    /**
     * Subtract the quaternion <code>q</code> from <code>this</code> quaternion
     * 
     * @param q the quaternion to subtract
     * @return this
     */
    public Quaternionf sub(Quaternionf q) {
        this.x -= q.x();
        this.y -= q.y();
        this.z -= q.z();
        this.w -= q.w();
        return this;
    }
    
    /**
     * Subtract the quaternion <code>q</code> from <code>this</code> quaternion and store
     * the result in the quaternion <code>dest</code>
     * 
     * @param q the quaternion to subtract
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf sub(Quaternionf q, Quaternionf dest) {
        dest.x = this.x - q.x();
        dest.y = this.y - q.y();
        dest.z = this.z - q.z();
        dest.w = this.w - q.w();
        return dest;
    }
    
    /**
     * Subtract the quaternion <tt>(x, y, z, w)</tt> from <code>this</code> quaternion
     * 
     * @param x component of the vector part
     * @param y component of the vector part
     * @param z component of the vector part
     * @param w the real part
     * @return this
     */
    public Quaternionf sub(float x, float y, float z, float w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }
    
    /**
     * Subtract the quaternion <tt>(x, y, z, w)</tt> form <code>this</code> quaternion and
     * store the result in the quaternion <code>dest</code>
     * 
     * @param x component of the vector part
     * @param y component of the vector part
     * @param z component of the vector part
     * @param w the real part
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf sub(float x, float y, float z, float w, Quaternionf dest) {
        dest.x = this.x - x;
        dest.y = this.y - y;
        dest.z = this.z - z;
        dest.w = this.w - w;
        return dest;
    }
    
    /**
     * Multiply <code>this</code> quaternion by the quaternion <code>q</code>
     * 
     * <p>
     * This corresponds to the multiplication <tt>R = T * Q</tt>
     * where <code>R</code> is the result, <code>T</code> is <code>this</code> and
     * <code>Q</code> is the quaternion <code>q</code>
     * </p>
     * 
     * So this method results in a vector to be transformed by <tt>Q</tt> first and then by <tt>T</tt>
     * 
     * @param q the quaternion to multiply with
     * @return this
     */
    public Quaternionf mul(Quaternionf q) {
        float newX = this.w * q.x() + this.x * q.w() + this.y * q.z() - this.z * q.y();
        float newY = this.w * q.y() - this.x * q.z() + this.y * q.w() + this.z * q.x();
        float newZ = this.w * q.z() + this.x * q.y() - this.y * q.x() + this.z * q.w();
        float newW = this.w * q.w() - this.x * q.x() - this.y * q.y() - this.z * q.z();
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;
        return this;
    }
    
    /**
     * Multiply <code>this</code> quaternion by the quaternion <code>q</code> and store the result
     * in the quaternion <code>dest</code>
     * 
     * <p>
     * This corresponds to the multiplication <tt>R = T * Q</tt>
     * where <code>R</code> is the result, <code>T</code> is <code>this</code> and
     * <code>Q</code> is the quaternion <code>q</code>
     * </p>
     * 
     * So this method results in a vector to be transformed by <tt>Q</tt> first and then by <tt>T</tt>
     * 
     * @param q the quaternion to multiply with
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf mul(Quaternionf q, Quaternionf dest) {
        dest.x = this.w * q.x() + this.x * q.w() + this.y * q.z() - this.z * q.y();
        dest.y = this.w * q.y() - this.x * q.z() + this.y * q.w() + this.z * q.x();
        dest.z = this.w * q.z() + this.x * q.y() - this.y * q.x() + this.z * q.w();
        dest.w = this.w * q.w() - this.x * q.x() - this.y * q.y() - this.z * q.z();
        return dest;
    }
    
    /**
     * Multiply <code>this</code> quaternion by the quaternion <tt>(x, y, z, w)</tt>
     * 
     * <p>
     * This corresponds to the multiplication <tt>R = T * Q</tt>
     * where <code>R</code> is the result, <code>T</code> is <code>this</code> and
     * <code>Q</code> is the quaternion <code>q</code>
     * </p>
     * 
     * So this method results in a vector to be transformed by <tt>Q</tt> first and then by <tt>T</tt>
     * 
     * @param x component of the vector part
     * @param y component of the vector part
     * @param z component of the vector part
     * @param w the real part
     * @return this
     */
    public Quaternionf mul(float x, float y, float z, float w) {
        float newX = this.w * x + this.x * w + this.y * z - this.z * y;
        float newY = this.w * y - this.x * z + this.y * w + this.z * x;
        float newZ = this.w * z + this.x * y - this.y * x + this.z * w;
        float newW = this.w * w - this.x * x - this.y * y - this.z * z;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;
        return this;
    }
    
    /**
     * Multiply <code>this</code> quaternion by the quaternion <code>q</code> and store the result
     * in the quaternion <code>dest</code>
     * 
     * <p>
     * This corresponds to the multiplication <tt>R = T * Q</tt>
     * where <code>R</code> is the result, <code>T</code> is <code>this</code> and
     * <code>Q</code> is the quaternion <code>q</code>
     * </p>
     * 
     * So this method results in a vector to be transformed by <tt>Q</tt> first and then by <tt>T</tt>
     * 
     * @param x component of the vector part
     * @param y component of the vector part
     * @param z component of the vector part
     * @param w the real part
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf mul(float x, float y, float z, float w, Quaternionf dest) {
        dest.x = this.w * x + this.x * w + this.y * z - this.z * y;
        dest.y = this.w * y - this.x * z + this.y * w + this.z * x;
        dest.z = this.w * z + this.x * y - this.y * x + this.z * w;
        dest.w = this.w * w - this.x * x - this.y * y - this.z * z;
        return dest;
    }
    
    /**
     * Apply the rotation described by <code>this</code> quaternion to a given {@link Vector3f} and
     * return it
     * 
     * @param v the vector to transform
     * @return the transformed vector
     */
    public Vector3f transform(Vector3f v) {
        float w2 = this.w * this.w;
        float x2 = this.x * this.x;
        float y2 = this.y * this.y;
        float z2 = this.z * this.z;
        float zw = this.z * this.w;
        float xy = this.x * this.y;
        float xz = this.x * this.z;
        float yw = this.y * this.w;
        float yz = this.y * this.z;
        float xw = this.x * this.w;
        float m00 = w2 + x2 - z2 - y2;
        float m01 = xy + zw + zw + xy;
        float m02 = xz - yw + xz - yw;
        float m10 = -zw + xy - zw + xy;
        float m11 = y2 - z2 + w2 - x2;
        float m12 = yz + yz + xw + xw;
        float m20 = yw + xz + xz + yw;
        float m21 = yz + yz - xw - xw;
        float m22 = z2 - y2 - x2 + w2;
        float newX = m00 * v.x() + m10 * v.y() + m20 * v.z();
        float newY = m01 * v.x() + m11 * v.y() + m21 * v.z();
        float newZ = m02 * v.x() + m12 * v.y() + m22 * v.z();
        return v.set(newX, newY, newZ);
    }
    
    /**
     * Apply the rotation described by <code>this</code> quaternion to a given {@link Vector3f} and
     * store the result in the vector <code>dest</code>
     * 
     * @param v the vector to transform
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f transform(Vector3f v, Vector3f dest) {
        float w2 = this.w * this.w;
        float x2 = this.x * this.x;
        float y2 = this.y * this.y;
        float z2 = this.z * this.z;
        float zw = this.z * this.w;
        float xy = this.x * this.y;
        float xz = this.x * this.z;
        float yw = this.y * this.w;
        float yz = this.y * this.z;
        float xw = this.x * this.w;
        float m00 = w2 + x2 - z2 - y2;
        float m01 = xy + zw + zw + xy;
        float m02 = xz - yw + xz - yw;
        float m10 = -zw + xy - zw + xy;
        float m11 = y2 - z2 + w2 - x2;
        float m12 = yz + yz + xw + xw;
        float m20 = yw + xz + xz + yw;
        float m21 = yz + yz - xw - xw;
        float m22 = z2 - y2 - x2 + w2;
        float newX = m00 * v.x() + m10 * v.y() + m20 * v.z();
        float newY = m01 * v.x() + m11 * v.y() + m21 * v.z();
        float newZ = m02 * v.x() + m12 * v.y() + m22 * v.z();
        return dest.set(newX, newY, newZ);
    }
    
    /**
     * Apply a rotation of a given angle about an axis represented as a {@link Vector3f} to
     * <code>this</code> quaternion
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     * 
     * @param axis the axis of the rotation
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Quaternionf rotate(Vector3f axis, float angle) {
        double hAngle = Math.toRadians(angle) * 0.5;
        double sin = Math.sin(hAngle);
        double invAxisLength = 1.0 / Math.sqrt((axis.x() * axis.x()) + (axis.y() * axis.y()) + (axis.z() * axis.z()));
        
        float rx = (float)(axis.x() * invAxisLength * sin);
        float ry = (float)(axis.y() * invAxisLength * sin);
        float rz = (float)(axis.z() * invAxisLength * sin);
        float rw = (float)Math.cos(hAngle);
        float newX = this.w * rx + this.x * rw + this.y * rz - this.z * ry;
        float newY = this.w * ry - this.x * rz + this.y * rw + this.z * rx;
        float newZ = this.w * rz + this.x * ry - this.y * rx + this.z * rw;
        float newW = this.w * rw - this.x * rx - this.y * ry - this.z * rz;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;
        return this;
    }
    
    /**
     * Apply a rotation of a given angle about an axis represented as a {@link Vector3f} to
     * <code>this</code> quaternion and store the result in the quaternion <code>dest</code>
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     * 
     * @param axis the axis of the rotation
     * @param angle the angle of rotation in degree
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf rotate(Vector3f axis, float angle, Quaternionf dest) {
        double hAngle = Math.toRadians(angle) * 0.5;
        double sin = Math.sin(hAngle);
        double invAxisLength = 1.0 / Math.sqrt((axis.x() * axis.x()) + (axis.y() * axis.y()) + (axis.z() * axis.z()));
        
        float rx = (float)(axis.x() * invAxisLength * sin);
        float ry = (float)(axis.y() * invAxisLength * sin);
        float rz = (float)(axis.z() * invAxisLength * sin);
        float rw = (float)Math.cos(hAngle);
        dest.x = this.w * rx + this.x * rw + this.y * rz - this.z * ry;
        dest.y = this.w * ry - this.x * rz + this.y * rw + this.z * rx;
        dest.z = this.w * rz + this.x * ry - this.y * rx + this.z * rw;
        dest.w = this.w * rw - this.x * rx - this.y * ry - this.z * rz;
        return dest;
    }
    
    /**
     * Apply a rotation of a given angle about an axis represented by the vector <tt>(x, y, z)</tt> to
     * <code>this</code> quaternion
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     * 
     * @param x component of the axis of rotation
     * @param y component of the axis of rotation
     * @param z component of the axis of rotation
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Quaternionf rotate(float x, float y, float z, float angle) {
        double hAngle = Math.toRadians(angle) * 0.5;
        double sin = Math.sin(hAngle);
        double invAxisLength = 1.0 / Math.sqrt((x * x) + (y * y) + (z * z));
        
        float rx = (float)(x * invAxisLength * sin);
        float ry = (float)(y * invAxisLength * sin);
        float rz = (float)(z * invAxisLength * sin);
        float rw = (float)Math.cos(hAngle);
        float newX = this.w * rx + this.x * rw + this.y * rz - this.z * ry;
        float newY = this.w * ry - this.x * rz + this.y * rw + this.z * rx;
        float newZ = this.w * rz + this.x * ry - this.y * rx + this.z * rw;
        float newW = this.w * rw - this.x * rx - this.y * ry - this.z * rz;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        this.w = newW;
        return this;
    }
    
    /**
     * Apply a rotation of a given angle about an axis represented by the vector <tt>(x, y, z)</tt> to
     * <code>this</code> quaternion and store the result in the quaternion <code>dest</code>
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     * 
     * @param x component of the axis of rotation
     * @param y component of the axis of rotation
     * @param z component of the axis of rotation
     * @param angle the angle of rotation in degree
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf rotate(float x, float y, float z, float angle, Quaternionf dest) {
        double hAngle = Math.toRadians(angle) * 0.5;
        double sin = Math.sin(hAngle);
        double invAxisLength = 1.0 / Math.sqrt((x * x) + (y * y) + (z * z));
        
        float rx = (float)(x * invAxisLength * sin);
        float ry = (float)(y * invAxisLength * sin);
        float rz = (float)(z * invAxisLength * sin);
        float rw = (float)Math.cos(hAngle);
        dest.x = this.w * rx + this.x * rw + this.y * rz - this.z * ry;
        dest.y = this.w * ry - this.x * rz + this.y * rw + this.z * rx;
        dest.z = this.w * rz + this.x * ry - this.y * rx + this.z * rw;
        dest.w = this.w * rw - this.x * rx - this.y * ry - this.z * rz;
        return dest;
    }
    
    /**
     * Apply a rotation of a given angle about the x axis to <code>this</code> quaternion
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     *
     * @see #rotate(Vector3f, float)
     *
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Quaternionf rotateX(float angle) {
        return rotate(Vector3f.AXIS_X, angle);
    }
    
    /**
     * Apply a rotation of a given angle about the x axis to <code>this</code> quaternion
     * and store the result in the quaternion <code>dest</code>
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     *
     * @see #rotate(Vector3f, float, Quaternionf)
     *
     * @param angle the angle of rotation in degree
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf rotateX(float angle, Quaternionf dest) {
        return rotate(Vector3f.AXIS_X, angle, dest);
    }
    
    /**
     * Apply a rotation of a given angle about the y axis to <code>this</code> quaternion
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     *
     * @see #rotate(Vector3f, float)
     *
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Quaternionf rotateY(float angle) {
        return rotate(Vector3f.AXIS_Y, angle);
    }
    
    /**
     * Apply a rotation of a given angle about the y axis to <code>this</code> quaternion
     * and store the result in the quaternion <code>dest</code>
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     *
     * @see #rotate(Vector3f, float, Quaternionf)
     * 
     * @param angle the angle of rotation in degree
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf rotateY(float angle, Quaternionf dest) {
        return rotate(Vector3f.AXIS_Y, angle, dest);
    }
    
    /**
     * Apply a rotation of a given angle about the z axis to <code>this</code> quaternion
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     *
     * @see #rotate(Vector3f, float)
     * 
     * @param angle the angle of rotation in degree
     * @return this
     */
    public Quaternionf rotateZ(float angle) {
        return rotate(Vector3f.AXIS_Z, angle);
    }
    
    /**
     * Apply a rotation of a given angle about the z axis to <code>this</code> quaternion
     * and store the result in the quaternion <code>dest</code>
     * 
     * <p>
     * If <code>T</code> is <code>this</code> quaternion and <code>Q</code> the specified rotation
     * then this method returns a quaternion equal to <code>T * Q</code> so when transforming a 
     * vector <code>v</code> with this new quaternion using {@link #transform} it will compute
     * <code>T * Q * v</code>, so the rotation added with this method will be applied first.
     * </p>
     *
     * @see #rotate(Vector3f, float, Quaternionf)
     *
     * @param angle the angle of rotation in degree
     * @param dest the quaternion to store the result in
     * @return the quaternion holding the result
     */
    public Quaternionf rotateZ(float angle, Quaternionf dest) {
        return rotate(Vector3f.AXIS_Z, angle, dest);
    }

    /**
     * Get the rotation matrix as a {@link Matrix4f} corresponding to the rotation of <code>this</code> quaternion
     * and store the result in <code>dest</code>
     *
     * @see Matrix4f#rotation(Quaternionf)
     *
     * @param dest the matrix to store the result into
     * @return the matrix holding the result
     */
    public Matrix4f rotationMatrix(Matrix4f dest) {
        return dest.rotation(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;            
        }
        if (obj instanceof Quaternionf) {
            Quaternionf other = (Quaternionf) obj;
            return other.x() == this.x && 
                   other.y() == this.y &&
                   other.z() == this.z &&
                   other.w() == this.w;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w +  ")";
    }
}
