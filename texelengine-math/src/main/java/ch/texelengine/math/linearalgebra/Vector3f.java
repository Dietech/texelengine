package ch.texelengine.math.linearalgebra;

/**
 * Represents a 3D vector of <code>float</code> values
 * 
 * <p>
 * Most of the operation on this object modify its values.
 * You therefore need to be careful and copy the object in some cases.
 * Alternatively you can use the copy methods that take another vector as an output object.
 * </p>
 * 
 * @author Dorian Ros
 *
 */
public final class Vector3f {

    /**
     * The x <code>float</code> component of the vector
     */
    private float x;
    
    /**
     * The y <code>float</code> component of the vector
     */
    private float y;
    
    /**
     * The z <code>float</code> component of the vector
     */
    private float z;
    
    /**
     * Unit vector in the x direction
     */
    public static final Vector3f AXIS_X = new Vector3f(1, 0, 0);
    
    /**
     * Unit vector in the y direction
     */
    public static final Vector3f AXIS_Y = new Vector3f(0, 1, 0);
    
    /**
     * Unit vector in the z direction
     */
    public static final Vector3f AXIS_Z = new Vector3f(0, 0, 1);
    
    /**
     * Construct a new {@link Vector3f}
     * 
     * @param x <code>float</code> component
     * @param y <code>float</code> component
     * @param z <code>float</code> component
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Construct a new {@link Vector3f} with each component set to 0
     */
    public Vector3f() {
        this(0, 0, 0);
    }
    
    /**
     * Construct a new {@link Vector3f} and copies the component data of the given vector <code>v</code>
     * 
     * @param v the vector to copy the values from
     */
    public Vector3f(Vector3f v) {
        this(v.x(), v.y(), v.z());
    }
    
    /**
     * Get the value of the x component of this vector
     * 
     * @return the x component as a <code>float</code>
     */
    public float x() {
        return x;
    }
    
    /**
     * Get the value of the y component of this vector
     * 
     * @return the y component as a <code>float</code>
     */
    public float y() {
        return y;
    }
    
    /**
     * Get the value of the z component of this vector
     * 
     * @return the z component as a <code>float</code>
     */
    public float z() {
        return z;
    }
    
    /**
     * Set the component of this vector to the given float values
     * 
     * @param x the x <code>float</code> component
     * @param y the y <code>float</code> component
     * @param z the z <code>float</code> component
     * @return this
     */
    public Vector3f set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    
    /**
     * Set the component of this vector to the values of the given vector <code>v</code>
     * 
     * @param v the vector to copy the values from
     * @return this
     */
    public Vector3f set(Vector3f v) {
        this.x = v.x();
        this.y = v.y();
        this.z = v.z();
        return this;
    }
    
    /**
     * Add the vector <code>v</code> to this vector
     * 
     * @param v the vector to add
     * @return this
     */
    public Vector3f add(Vector3f v) {
        this.x += v.x();
        this.y += v.y();
        this.z += v.z();
        return this;
    }
    
    /**
     * Add the vector <code>v</code> to this vector and store
     * the result in the vector <code>dest</code>
     * 
     * @param v the vector to add
     * @param dest the vector to store the result in
     * @return a vector holding the result
     */
    public Vector3f add(Vector3f v, Vector3f dest) {
        dest.x = this.x + v.x();
        dest.y = this.y + v.y();
        dest.z = this.z + v.z();
        return dest;
    }
    
    /**
     * Add the <code>float</code> component values <code>x</code> and <code>y</code> to this vector
     * 
     * @param x <code>float</code> component to add
     * @param y <code>float</code> component to add
     * @param z <code>float</code> component to add
     * @return this
     */
    public Vector3f add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    
    /**
     * Add the <code>float</code> component values <code>x</code> and <code>y</code> to this vector
     * and stores the result in the vector <code>dest</code>
     * 
     * @param x <code>float</code> component to add
     * @param y <code>float</code> component to add
     * @param z <code>float</code> component to add
     * @param dest the vector to store the result in
     * @return a vector holding the result
     */
    public Vector3f add(float x, float y, float z, Vector3f dest) {
        dest.x = this.x + x;
        dest.y = this.y + y;
        dest.z = this.z + z;
        return dest;
    }
    
    /**
     * Subtract the vector <code>v</code> from this vector
     * 
     * @param v the vector to subtract
     * @return this
     */
    public Vector3f sub(Vector3f v) {
        this.x -= v.x();
        this.y -= v.y();
        this.z -= v.z();
        return this;
    }
    
    /**
     * Subtract the vector <code>v</code> from this vector and stores 
     * the result in the vector <code>dest</code>
     * 
     * @param v the vector to subtract
     * @param dest the vector to store the result in
     * @return a vector holding the result
     */
    public Vector3f sub(Vector3f v, Vector3f dest) {
        dest.x = this.x - v.x();
        dest.y = this.y - v.y();
        dest.z = this.z - v.z();
        return dest;
    }
    
    /**
     * Subtract the <code>float</code> component values <code>x</code> and <code>y</code> from this vector
     * 
     * @param x <code>float</code> component to subtract
     * @param y <code>float</code> component to subtract
     * @param z <code>float</code> component to subtract
     * @return this
     */
    public Vector3f sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }
    
    /**
     * Subtract the <code>float</code> component values <code>x</code> and <code>y</code> from this vector
     * and stores the result in the vector <code>dest</code>
     * 
     * @param x <code>float</code> component to subtract
     * @param y <code>float</code> component to subtract
     * @param z <code>float</code> component to subtract
     * @param dest the vector to store the result in
     * @return a vector holding the result
     */
    public Vector3f sub(float x, float y, float z, Vector3f dest) {
        dest.x = this.x - x;
        dest.y = this.y - y;
        dest.z = this.z - z;
        return dest;
    }
    
    /**
     * Return the dot product of this vector and the vector <code>v</code>
     * 
     * @param v the other vector
     * @return the dot product
     */
    public float dot(Vector3f v) {
        return this.x * v.x() + this.y * v.y() + this.z * v.z();
    }
    
    /**
     * Set this vector to be the cross product of itself and the vector <code>v</code>
     * 
     * @param v the vector to calculate the cross product with
     * @return this
     */
    public Vector3f cross(Vector3f v) {
        float newX = this.y * v.z() - this.z * v.y();
        float newY = this.z * v.x() - this.x * v.z();
        float newZ = this.x * v.y() - this.y * v.x();
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        return this;
    }
    
    /**
     * Calculate the cross product of this vector and the vector <code>v</code>
     * and store the result in the vector <code>dest</code>
     * 
     * @param v the vector to calculate the cross product with
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f cross(Vector3f v, Vector3f dest) {
        dest.x = this.y * v.z() - this.z * v.y();
        dest.y = this.z * v.x() - this.x * v.z();
        dest.z = this.x * v.y() - this.y * v.x();
        return dest;
    }
    
    /**
     * Set this vector to be the cross product of itself and the vector <tt>(x, y, z)</tt>
     * 
     * @param x component of the other vector
     * @param y component of the other vector
     * @param z component of the other vector
     * @return this
     */
    public Vector3f cross(float x, float y, float z) {
        float newX = this.y * z - this.z * y;
        float newY = this.z * x - this.x * z;
        float newZ = this.x * y - this.y * x;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        return this;
    }
    
    /**
     * Calculate the cross product of this vector and the vector <tt>(x, y ,z)</tt>
     * and store the result in the vector <code>dest</code>
     * 
     * @param x component of the other vector
     * @param y component of the other vector
     * @param z component of the other vector
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f cross(float x, float y, float z, Vector3f dest) {
        dest.x = this.y * z - this.z * y;
        dest.y = this.z * x - this.x * z;
        dest.z = this.x * y - this.y * x;
        return dest;
    }
    
    /**
     * Set this vector's components to their respective absolute value
     * 
     * @return this
     */
    public Vector3f absolute() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
        this.z = Math.abs(this.z);
        return this;
    }
    
    /**
     * Set the <code>dest</code> vector's components to the respective absolute values
     * of this vector's components
     * 
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f absolute(Vector3f dest) {
        dest.x = Math.abs(this.x);
        dest.y = Math.abs(this.y);
        dest.z = Math.abs(this.z);
        return dest;
    }
    
    /**
     * Reflect <code>this</code> incomming vector about the given normal vector <code>n</code>
     * 
     * @param n the normal vector to reflect about
     * @return this
     */
    public Vector3f reflect(Vector3f n) {
        float dot = this.x * n.x() + this.y * n.y() + this.z * n.z();
        this.x -= (dot + dot) * n.x();
        this.y -= (dot + dot) * n.y();
        this.z -= (dot + dot) * n.z();
        return this;
    }
    
    /**
     * Reflect <code>this</code> incomming vector about the given normal vector <code>n</code> and
     * store the result in the vector <code>dest</code>
     * 
     * @param n the normal vector to reflect about
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f reflect(Vector3f n, Vector3f dest) {
        float dot = this.x * n.x() + this.y * n.y() + this.z * n.z();
        dest.x = this.x - (dot + dot) * n.x();
        dest.y = this.y - (dot + dot) * n.y();
        dest.z = this.z - (dot + dot) * n.z();
        return dest;
    }
    
    /**
     * Reflect this vector about the given normal vector <tt>(x, y, z)</tt>
     * 
     * @param x component of the normal vector
     * @param y component of the normal vector
     * @param z component of the normal vector
     * @return this
     */
    public Vector3f reflect(float x, float y, float z) {
        float dot = this.x * x + this.y * y + this.z * z;
        this.x -= (dot + dot) * x;
        this.y -= (dot + dot) * y;
        this.z -= (dot + dot) * z;
        return this;
    }
    
    /**
     * Reflect this vector about the given normal vector <tt>(x, y, z)</tt> and
     * store the result in the vector <code>dest</code>
     * 
     * @param x component of the normal vector
     * @param y component of the normal vector
     * @param z component of the normal vector
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f reflect(float x, float y, float z, Vector3f dest) {
        float dot = this.x * x + this.y * y + this.z * z;
        dest.x = this.x - (dot + dot) * x;
        dest.y = this.y - (dot + dot) * y;
        dest.z = this.z - (dot + dot) * z;
        return dest;
    }
    
    /**
     * Linearly interpolate this vector and the vector <code>v</code> using the given interpolation factor
     * 
     * @param v the vector to interpolate with
     * @param factor the interpolation factor between 0.0 and 1.0
     * @return this
     */
    public Vector3f lerp(Vector3f v, float factor) {
        this.x += (this.x - v.x()) * factor;
        this.y += (this.y - v.y()) * factor;
        this.z += (this.z - v.z()) * factor;
        return this;
    }
    
    /**
     * Linearly interpolate this vector and the vector <code>v</code> using the given interpolation factor
     * and store the result in the vector <code>dest</code>
     * 
     * @param v the vector to interpolate with
     * @param factor the interpolation factor between 0.0 and 1.0
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f lerp(Vector3f v, float factor, Vector3f dest) {
        dest.x = this.x + ((this.x - v.x()) * factor);
        dest.y = this.y + ((this.y - v.y()) * factor);
        dest.z = this.z + ((this.z - v.z()) * factor);
        return dest;
    }
    
    /**
     * Rotates this vector around the X axis <tt>(1, 0, 0)</tt> by a given angle
     * 
     * @param angle the angle in degree
     * @return this
     */
    public Vector3f rotateX(float angle) {
        double rad = Math.toRadians(angle);
        float sin = (float)Math.sin(rad);
        float cos = (float)Math.cos(rad);
        float newY = this.y * cos - this.z * sin;
        float newZ = this.y * sin + this.z * cos;
        this.y = newY;
        this.z = newZ;
        return this;
    }
    
    /**
     * Rotates this vector around the X axis <tt>(1, 0, 0)</tt> by a given angle
     * and store the result in the vector <code>dest</code>
     * 
     * @param angle the angle in degree
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f rotateX(float angle, Vector3f dest) {
        double rad = Math.toRadians(angle);
        float sin = (float)Math.sin(rad);
        float cos = (float)Math.cos(rad);
        dest.x = this.x;
        dest.y = this.y * cos - this.z * sin;
        dest.z = this.y * sin + this.z * cos;
        return dest;
    }
    
    /**
     * Rotates this vector around the Y axis <tt>(0, 1, 0)</tt> by a given angle
     * 
     * @param angle the angle in degree
     * @return this
     */
    public Vector3f rotateY(float angle) {
        double rad = Math.toRadians(angle);
        float sin = (float)Math.sin(rad);
        float cos = (float)Math.cos(rad);
        float newX =  this.x * cos + this.z * sin;
        float newZ = -this.x * sin + this.z * cos;
        this.x = newX;
        this.z = newZ;
        return this;
    }
    
    /**
     * Rotates this vector around the Y axis <tt>(0, 1, 0)</tt> by a given angle
     * and store the result in the vector <code>dest</code>
     * 
     * @param angle the angle in degree
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f rotateY(float angle, Vector3f dest) {
        double rad = Math.toRadians(angle);
        float sin = (float)Math.sin(rad);
        float cos = (float)Math.cos(rad);
        dest.x = this.x * cos + this.z * sin;
        dest.y = this.y;
        dest.z = -this.x * sin + this.z * cos;
        return dest;
    }
    
    /**
     * Rotates this vector around the Z axis <tt>(0, 0, 1)</tt> by a given angle
     * 
     * @param angle the angle in degree
     * @return this
     */
    public Vector3f rotateZ(float angle) {
        double rad = Math.toRadians(angle);
        float sin = (float)Math.sin(rad);
        float cos = (float)Math.cos(rad);
        float newX = this.x * cos - this.y * sin;
        float newY = this.x * sin + this.y * cos;
        this.x = newX;
        this.y = newY;
        return this;
    }
    
    /**
     * Rotates this vector around the Z axis <tt>(0, 0, 1)</tt> by a given angle
     * and store the result in the vector <code>dest</code>
     * 
     * @param angle the angle in degree
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f rotateZ(float angle, Vector3f dest) {
        double rad = Math.toRadians(angle);
        float sin = (float)Math.sin(rad);
        float cos = (float)Math.cos(rad);
        dest.x = this.x * cos - this.y * sin;
        dest.y = this.x * sin + this.y * cos;
        dest.z = this.z;
        return dest;
    }
    
    /**
     * Returns the length of this vector
     * 
     * @return the length
     */
    public float length() {
        return (float)Math.sqrt((this.x * this.x) + (this.y * this.y)+ (this.z * this.z));
    }
    
    /**
     * Returns the length squared of this vector
     * 
     * @return the length squared
     */
    public float lengthSquared() {
        return (this.x * this.x) + (this.y * this.y) + (this.z * this.z);
    }
    
    /**
     * Normalize this vector
     * 
     * @return this
     */
    public Vector3f normalize() {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z)));
        this.x *= invLength;
        this.y *= invLength;
        this.z *= invLength;
        return this;
    }
    
    /**
     * Normalize this vector and store the result in the vector <code>dest</code>
     * 
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f normalize(Vector3f dest) {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z)));
        dest.x = this.x * invLength;
        dest.y = this.y * invLength;
        dest.z = this.z * invLength;
        return dest;
    }
    
    /**
     * Scale this vector to have the given length
     * 
     * @param length the desired length
     * @return this
     */
    public Vector3f normalize(float length) {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z))) * length;
        this.x *= invLength;
        this.y *= invLength;
        this.z *= invLength;
        return this;
    }
    
    /**
     * Scale this vector to have the given length and store the result in the vector <code>dest</code>
     * 
     * @param length the desired length
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f normalize(float length, Vector3f dest) {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z))) * length;
        dest.x = this.x * invLength;
        dest.y = this.y * invLength;
        dest.z = this.z * invLength;
        return dest;
    }
    
    /**
     * Negate this vector
     * 
     * @return this
     */
    public Vector3f negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }
    
    /**
     * Negate this vector and store the result in the vector <code>dest</code>
     * 
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f negate(Vector3f dest) {
        dest.x = -this.x;
        dest.y = -this.y;
        dest.z = -this.z;
        return dest;
    }
    
    /**
     * Multiply this vector by a scalar value
     * 
     * @param scalar the value to multiply this vector with
     * @return this
     */
    public Vector3f mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }
    
    /**
     * Multiply this vector by a scalar value and store the result in the vector <code>dest</code>
     * 
     * @param scalar the value to multiply this vector with
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f mul(float scalar, Vector3f dest) {
        dest.x = this.x * scalar;
        dest.y = this.y * scalar;
        dest.z = this.z * scalar;
        return dest;
    }
    
    /**
     * Multiply this vector component-wise by the vector <code>v</code>
     * 
     * @param v the vector to multiply by
     * @return this
     */
    public Vector3f mul(Vector3f v) {
        this.x *= v.x();
        this.y *= v.y();
        this.z *= v.z();
        return this;
    }
    
    /**
     * Multiply this vector component-wise by the vector <code>v</code> and store the
     * result in the vector <code>dest</code>
     * 
     * @param v the vector to multiply by
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f mul(Vector3f v, Vector3f dest) {
        dest.x = this.x * v.x();
        dest.y = this.y * v.y();
        dest.z = this.z * v.z();
        return dest;
    }
    
    /**
     * Multiply this vector's component by the respective <code>x</code> and <code>y</code> components
     * 
     * @param x <code>float</code> component value to multiply by
     * @param y <code>float</code> component value to multiply by
     * @param z <code>float</code> component value to multiply by
     * @return this
     */
    public Vector3f mul(float x, float y, float z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }
    
    /**
     * Multiply this vector's component by the respective <code>x</code> and <code>y</code> components
     * and store the result in the vector <code>dest</code>
     * 
     * @param x <code>float</code> component value to multiply by
     * @param y <code>float</code> component value to multiply by
     * @param z <code>float</code> component value to multiply by
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f mul(float x, float y, float z, Vector3f dest) {
        dest.x = this.x * x;
        dest.y = this.y * y;
        dest.z = this.z * z;
        return dest;
    }
    
    /**
     * Rotate <code>this</code> vector by the given quaternion
     * 
     * @param q the quaternion to rotate <code>this</code> vector
     * @return <code>this</code>
     */
    public Vector3f rotate(Quaternionf q) {
        return q.transform(this);
    }
    
    /**
     * Rotate <code>this</code> vector by the given quaternion and store
     * the result in the vector <code>dest</code>
     * 
     * @param q the quaternion to rotate <code>this</code> vector
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f rotate(Quaternionf q, Vector3f dest) {
        return q.transform(this, dest);
    }
    
    /**
     * Rotate <code>this</code> vector by an angle about an axis. Positive angles 
     * result in clockwise rotation
     * 
     * @param axis the axis of rotation
     * @param angle the angle of rotation
     * @return this
     */
    public Vector3f rotate(Vector3f axis, float angle) {
        double hAngle = Math.toRadians(angle) * 0.5;
        float sin = (float)Math.sin(hAngle);
        float qx = axis.x() * sin;
        float qy = axis.y() * sin;
        float qz = axis.z() * sin;
        float qw = (float)Math.cos(hAngle);
        float w2 = qw * qw;
        float x2 = qx * qx;
        float y2 = qy * qy;
        float z2 = qz * qz;
        float zw = qz * qw;
        float xy = qx * qy;
        float xz = qx * qz;
        float yw = qy * qw;
        float yz = qy * qz;
        float xw = qx * qw;
        float nx = (w2 + x2 - z2 - y2) * this.x + (-zw + xy - zw + xy) * this.y + (yw + xz + xz + yw) * this.z;
        float ny = (xy + zw + zw + xy) * this.x + ( y2 - z2 + w2 - x2) * this.y + (yz + yz - xw - xw) * this.z;
        float nz = (xz - yw + xz - yw) * this.x + ( yz + yz + xw + xw) * this.y + (z2 - y2 - x2 + w2) * this.z;
        this.x = nx;
        this.y = ny;
        this.z = nz;
        return this;
    }
    
    /**
     * Rotate <code>this</code> vector by an angle about an axis and 
     * store the result in the vector <code>dest</code>. Positive angles 
     * result in clockwise rotation
     * 
     * @param axis the axis of rotation
     * @param angle the angle of rotation
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f rotate(Vector3f axis, float angle, Vector3f dest) {
        double hAngle = Math.toRadians(angle) * 0.5;
        float sin = (float)Math.sin(hAngle);
        float qx = axis.x() * sin;
        float qy = axis.y() * sin;
        float qz = axis.z() * sin;
        float qw = (float)Math.cos(hAngle);
        float w2 = qw * qw;
        float x2 = qx * qx;
        float y2 = qy * qy;
        float z2 = qz * qz;
        float zw = qz * qw;
        float xy = qx * qy;
        float xz = qx * qz;
        float yw = qy * qw;
        float yz = qy * qz;
        float xw = qx * qw;
        dest.x = (w2 + x2 - z2 - y2) * this.x + (-zw + xy - zw + xy) * this.y + (yw + xz + xz + yw) * this.z;
        dest.y = (xy + zw + zw + xy) * this.x + ( y2 - z2 + w2 - x2) * this.y + (yz + yz - xw - xw) * this.z;
        dest.z = (xz - yw + xz - yw) * this.x + ( yz + yz + xw + xw) * this.y + (z2 - y2 - x2 + w2) * this.z;
        return dest;
    }
    
    /**
     * Transform <code>this</code> by the given {@link Matrix4f} transformation
     * 
     * <p>
     * Corresponds to <tt>M * v</tt> where <code>M</code> is the transformation
     * matrix and <code>v</code> is <code>this</code> vector
     * </p>
     * 
     * @param transformation the transformation matrix
     * @return this
     */
    public Vector3f transform(Matrix4f transformation) {
        return transformation.transform(this);
    }
    
    /**
     * Transform <code>this</code> by the given {@link Matrix4f} transformation and
     * store the result in the <code>dest</code> vector
     * 
     * <p>
     * Corresponds to <tt>M * v</tt> where <code>M</code> is the transformation
     * matrix and <code>v</code> is <code>this</code> vector
     * </p>
     * 
     * @param transformation the transformation matrix
     * @param dest the vector to store the result into
     * @return the vector holding the result
     */
    public Vector3f transform(Matrix4f transformation, Vector3f dest) {
        return transformation.transform(this, dest);
    }
    
    /**
     * Set the component of this vector to be the component-wise minimum of this and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @return this
     */
    public Vector3f min(Vector3f v) {
        this.x = Math.min(this.x, v.x());
        this.y = Math.min(this.y, v.y());
        this.z = Math.min(this.z, v.z());
        return this;
    }
    
    /**
     * Set the component of the <code>dest</code> vector to be the component-wise minimum of this vector and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f min(Vector3f v, Vector3f dest) {
        dest.x = Math.min(this.x, v.x());
        dest.y = Math.min(this.y, v.y());
        dest.z = Math.min(this.z, v.z());
        return dest;
    }
    
    /**
     * Set the component of this vector to be the component-wise maximum of this and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @return this
     */
    public Vector3f max(Vector3f v) {
        this.x = Math.max(this.x, v.x());
        this.y = Math.max(this.y, v.y());
        this.z = Math.max(this.z, v.z());
        return this;
    }
    
    /**
     * Set the component of the <code>dest</code> vector to be the component-wise maximum of this vector and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector3f max(Vector3f v, Vector3f dest) {
        dest.x = Math.max(this.x, v.x());
        dest.y = Math.max(this.y, v.y());
        dest.z = Math.max(this.z, v.z());
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
        if (obj instanceof Vector3f) {
            Vector3f other = (Vector3f) obj;
            return other.x() == this.x && 
                   other.y() == this.y &&
                   other.z() == this.z;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
