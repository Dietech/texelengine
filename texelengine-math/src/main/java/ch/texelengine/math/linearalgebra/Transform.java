package ch.texelengine.math.linearalgebra;

/**
 * Represent a transformation in 3D space and can provide
 * a transformation matrix
 *
 * @author Dorian Ros
 */
public class Transform {

    /**
     * Position {@link Vector3f} in 3D space
     */
    private Vector3f position;

    /**
     * Scale {@link Vector3f} in all 3 axis
     */
    private Vector3f scale;

    /**
     * Rotation {@link Quaternionf} in 3D space
     */
    private Quaternionf rotation;

    /**
     * Transform {@link Matrix4f} combines a position, rotation
     * and scale information
     */
    private Matrix4f transform;

    /**
     * Construct a new {@link Transform} and set it to the identity
     */
    public Transform() {
        this.position = new Vector3f();
        this.rotation = new Quaternionf();
        this.scale = new Vector3f(1, 1, 1);
        this.transform = new Matrix4f();
    }

    /**
     * Calculate the transformation matrix according to the current position,
     * rotation and scale parameters
     */
    public void calculateTransformation() {
        this.transform.translationRotationScale(this.position, this.rotation, this.scale);
    }

    /**
     * Get the transform {@link Matrix4f} of <code>this</code> transform object
     *
     * @return the transformation matrix for the transform object
     */
    public Matrix4f transform() {
        return this.transform;
    }

    /**
     * Move <code>this</code> transform object in 3D space
     *
     * @param xOffset the offset in position on the x axis
     * @param yOffset the offset in position on the y axis
     * @param zOffset the offset in position on the z axis
     */
    public void move(float xOffset, float yOffset, float zOffset) {
        this.position.add(xOffset, yOffset, zOffset);
    }

    /**
     * Scale <code>this</code> transform object on the 3 axis
     *
     * @param xScale the scale factor on the x axis
     * @param yScale the scale factor on the y axis
     * @param zScale the scale factor on the z axis
     */
    public void scale(float xScale, float yScale, float zScale) {
        this.scale.mul(xScale, yScale, zScale);
    }

    /**
     * Rotate <code>this</code> transform in 3D space
     *
     * @param axis the axis of rotation as a {@link Vector3f}
     * @param angle the angle of rotation in degree
     */
    public void rotate(Vector3f axis, float angle) {
        this.rotation.rotate(axis, angle);
    }

    /**
     * Get the position vector of <code>this</code> transformation as a {@link Vector3f}
     *
     * @return the position vector
     */
    public Vector3f position() {
        return this.position;
    }

    /**
     * Get the rotation of <code>this</code> transformation as a {@link Quaternionf}
     *
     * @return the rotation quaternion
     */
    public Quaternionf rotation() {
        return this.rotation;
    }

    /**
     * Get the scale vector of <code>this</code> transformation as a {@link Vector3f}
     *
     * @return the scale vector
     */
    public Vector3f scale() {
        return this.scale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Transform) {
            Transform other = (Transform) obj;
            return other.scale.equals(this.scale) &&
                   other.rotation.equals(this.rotation) &&
                   other.position.equals(this.position);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Position: " + this.position.toString() +
               ", Rotation: " + this.rotation.toString() +
               ", Scale: " + this.rotation.toString();
    }
}
