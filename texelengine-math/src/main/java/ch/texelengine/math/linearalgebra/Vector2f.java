package ch.texelengine.math.linearalgebra;

/**
 * Represents a 2D vector of <code>float</code> values
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
public final class Vector2f {

    /**
     * The x <code>float</code> component of the vector
     */
	    private float x;
	    
	    /**
	     * The y <code>float</code> component of the vector
	     */
	    private float y;
	    
	    /**
	     * Unit vector in the x direction
	     */
	    public static final Vector2f AXIS_X = new Vector2f(1, 0);
	    
	    /**
	     * Unit vector in the y direction
	     */
	    public static final Vector2f AXIS_Y = new Vector2f(0, 1);
	    
	    /**
	     * Construct a new {@link Vector2f}
	     * 
	     * @param x <code>float</code> component
	     * @param y <code>float</code> component
	     */
	    public Vector2f(float x, float y) {
	        this.x = x;
	        this.y = y;
	    }
	    
	    /**
	     * Construct a new {@link Vector2f} with each component set to 0
	     */
	    public Vector2f() {
	        this(0, 0);
	    }
	    
	    /**
	     * Construct a new {@link Vector2f} and copies the component data of the given vector <code>v</code>
	     * 
	     * @param v the vector to copy the values from
	     */
	    public Vector2f(Vector2f v) {
	        this(v.x(), v.y());
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
	     * Set the component of this vector to the given float values
	     * 
	     * @param x the x <code>float</code> component
	     * @param y the y <code>float</code> component
	     * @return this
	     */
	    public Vector2f set(float x, float y) {
	        this.x = x;
	        this.y = y;
	        return this;
	    }
	    
	    /**
	     * Set the component of this vector to the values of the given vector <code>v</code>
	     * 
	     * @param v the vector to copy the values from
	     * @return this
	     */
	    public Vector2f set(Vector2f v) {
	        this.x = v.x();
	        this.y = v.y();
	        return this;
	    }
	    
	    /**
	     * Add the vector <code>v</code> to this vector
	     * 
	     * @param v the vector to add
	     * @return this
	     */
	    public Vector2f add(Vector2f v) {
	        this.x += v.x();
	        this.y += v.y();
	        return this;
	    }
	    
	    /**
	     * Add the vector <code>v</code> to this vector and stores 
	     * the result in the vector <code>dest</code>
	     * 
	     * @param v the vector to add
	     * @param dest the vector to store the result in
	     * @return a vector holding the result
	     */
	    public Vector2f add(Vector2f v, Vector2f dest) {
	        dest.x = this.x + v.x();
	        dest.y = this.y + v.y();
	        return dest;
	    }
	    
	    /**
	     * Add the <code>float</code> component values <code>x</code> and <code>y</code> to this vector
	     * 
	     * @param x <code>float</code> component to add
	     * @param y <code>float</code> component to add
	     * @return this
	     */
	    public Vector2f add(float x, float y) {
	        this.x += x;
	        this.y += y;
	        return this;
	    }
	    
	    /**
	     * Add the <code>float</code> component values <code>x</code> and <code>y</code> to this vector
	     * and stores the result in the vector <code>dest</code>
	     * 
	     * @param x <code>float</code> component to add
	     * @param y <code>float</code> component to add
	     * @param dest the vector to store the result in
	     * @return a vector holding the result
	     */
	    public Vector2f add(float x, float y, Vector2f dest) {
	        dest.x = this.x + x;
	        dest.y = this.y + y;
	        return dest;
	    }
	    
	    /**
     * Subtract the vector <code>v</code> from this vector
     * 
     * @param v the vector to subtract
     * @return this
     */
    public Vector2f sub(Vector2f v) {
        this.x -= v.x();
        this.y -= v.y();
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
    public Vector2f sub(Vector2f v, Vector2f dest) {
        dest.x = this.x - v.x();
        dest.y = this.y - v.y();
        return dest;
    }
    
    /**
     * Subtract the <code>float</code> component values <code>x</code> and <code>y</code> from this vector
     * 
     * @param x <code>float</code> component to subtract
     * @param y <code>float</code> component to subtract
     * @return this
     */
    public Vector2f sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }
    
    /**
     * Subtract the <code>float</code> component values <code>x</code> and <code>y</code> from this vector
     * and stores the result in the vector <code>dest</code>
     * 
     * @param x <code>float</code> component to subtract
     * @param y <code>float</code> component to subtract
     * @param dest the vector to store the result in
     * @return a vector holding the result
     */
    public Vector2f sub(float x, float y, Vector2f dest) {
        dest.x = this.x - x;
        dest.y = this.y - y;
        return dest;
    }
    
    /**
     * Return the dot product of this vector and the vector <code>v</code>
     * 
     * @param v the other vector
     * @return the dot product
     */
    public float dot(Vector2f v) {
        return this.x * v.x() + this.y * v.y();
    }
    
    /**
     * Returns the length of this vector
     * 
     * @return the length
     */
    public float length() {
        return (float)Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
    
    /**
     * Returns the length squared of this vector
     * 
     * @return the length squared
     */
    public float lengthSquared() {
        return (this.x * this.x) + (this.y * this.y);
    }
    
    /**
     * Normalize this vector
     * 
     * @return this
     */
    public Vector2f normalize() {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y)));
        this.x *= invLength;
        this.y *= invLength;
        return this;
    }
    
    /**
     * Normalize this vector and store the result in the vector <code>dest</code>
     * 
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f normalize(Vector2f dest) {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y)));
        dest.x = this.x * invLength;
        dest.y = this.y * invLength;
        return dest;
    }
    
    /**
     * Scale this vector to have the given length
     * 
     * @param length the desired length
     * @return this
     */
    public Vector2f normalize(float length) {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y))) * length;
        this.x *= invLength;
        this.y *= invLength;
        return this;
    }
    
    /**
     * Scale this vector to have the given length and store the result in the vector <code>dest</code>
     * 
     * @param length the desired length
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f normalize(float length, Vector2f dest) {
        float invLength = (float)(1.0 / Math.sqrt((this.x * this.x) + (this.y * this.y))) * length;
        dest.x = this.x * invLength;
        dest.y = this.y * invLength;
        return dest;
    }
    
    /**
     * Negate this vector
     * 
     * @return this
     */
    public Vector2f negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }
    
    /**
     * Negate this vector and store the result in the vector <code>dest</code>
     * 
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f negate(Vector2f dest) {
        dest.x = -this.x;
        dest.y = -this.y;
        return dest;
    }
    
    /**
     * Multiply this vector by a scalar value
     * 
     * @param scalar the value to multiply this vector with
     * @return this
     */
    public Vector2f mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }
    
    /**
     * Multiply this vector by a scalar value and store the result in the vector <code>dest</code>
     * 
     * @param scalar the value to multiply this vector with
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f mul(float scalar, Vector2f dest) {
        dest.x = this.x * scalar;
        dest.y = this.y * scalar;
        return dest;
    }
    
    /**
     * Multiply this vector component-wise by the vector <code>v</code>
     * 
     * @param v the vector to multiply by
     * @return this
     */
    public Vector2f mul(Vector2f v) {
        this.x *= v.x();
        this.y *= v.y();
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
    public Vector2f mul(Vector2f v, Vector2f dest) {
        dest.x = this.x * v.x();
        dest.y = this.y * v.y();
        return dest;
    }
    
    /**
     * Multiply this vector's component by the respective <code>x</code> and <code>y</code> components
     * 
     * @param x <code>float</code> component value to multiply by
     * @param y <code>float</code> component value to multiply by
     * @return this
     */
    public Vector2f mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }
    
    /**
     * Multiply this vector's component by the respective <code>x</code> and <code>y</code> components
     * and store the result in the vector <code>dest</code>
     * 
     * @param x <code>float</code> component value to multiply by
     * @param y <code>float</code> component value to multiply by
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f mul(float x, float y, Vector2f dest) {
        dest.x = this.x * x;
        dest.y = this.y * y;
        return dest;
    }
    
    /**
     * Rotates this vector by the given angle in degree. Positive angles result in anti-clockwise rotation
     * and negative angles result in clockwise rotation
     * 
     * @param angle the angle to rotate by
     * @return this
     */
    public Vector2f rotate(float angle) {
        //The radian conversion is done here
        double angleRad = Math.toRadians(angle);
        
        double cosAngle = Math.cos(angleRad);
        double sinAngle = Math.sin(angleRad);
        
        float newX = (float)((this.x * cosAngle) - (this.y * sinAngle));
        float newY = (float)((this.x * sinAngle) + (this.y * cosAngle));
        this.x = newX;
        this.y = newY;
        return this;
    }
    
    /**
     * Rotates this vector by the given angle in degree and store the result in the vector <code>dest</code>.
     * Positive angles result in anti-clockwise rotation and negative angles result in clockwise rotation
     * 
     * @param angle the angle to rotate by
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f rotate(float angle, Vector2f dest) {
        //The radian conversion is done here
        double angleRad = Math.toRadians(angle);
        
        double cosAngle = Math.cos(angleRad);
        double sinAngle = Math.sin(angleRad);
        
        float newX = (float)((this.x * cosAngle) - (this.y * sinAngle));
        float newY = (float)((this.x * sinAngle) + (this.y * cosAngle));
        dest.x = newX;
        dest.y = newY;
        return dest;
    }
    
    /**
     * Linearly interpolate this vector and the vector <code>v</code> using the given interpolation factor
     * 
     * @param v the vector to interpolate with
     * @param factor the interpolation factor between 0.0 and 1.0
     * @return this
     */
    public Vector2f lerp(Vector2f v, float factor) {
        this.x += (this.x - v.x()) * factor;
        this.y += (this.y - v.y()) * factor;
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
    public Vector2f lerp(Vector2f v, float factor, Vector2f dest) {
        dest.x = this.x + ((this.x - v.x()) * factor);
        dest.y = this.y + ((this.y - v.y()) * factor);
        return dest;
    }
    
    /**
     * Set the component of this vector to be the component-wise minimum of this and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @return this
     */
    public Vector2f min(Vector2f v) {
        this.x = Math.min(this.x, v.x());
        this.y = Math.min(this.y, v.y());
        return this;
    }
    
    /**
     * Set the component of the <code>dest</code> vector to be the component-wise minimum of this vector and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f min(Vector2f v, Vector2f dest) {
        dest.x = Math.min(this.x, v.x());
        dest.y = Math.min(this.y, v.y());
        return dest;
    }
    
    /**
     * Set the component of this vector to be the component-wise maximum of this and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @return this
     */
    public Vector2f max(Vector2f v) {
        this.x = Math.max(this.x, v.x());
        this.y = Math.max(this.y, v.y());
        return this;
    }
    
    /**
     * Set the component of the <code>dest</code> vector to be the component-wise maximum of this vector and the vector <code>v</code>
     * 
     * @param v the vector to compare against
     * @param dest the vector to store the result in
     * @return the vector holding the result
     */
    public Vector2f max(Vector2f v, Vector2f dest) {
        dest.x = Math.max(this.x, v.x());
        dest.y = Math.max(this.y, v.y());
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
        if (obj instanceof Vector2f) {
            Vector2f other = (Vector2f) obj;
            return other.x() == this.x && other.y() == this.y;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
