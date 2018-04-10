package ch.texelengine.engine.api.buffers;

import ch.texelengine.engine.platform.opengl.buffers.GLVertexBuffer;

import java.nio.FloatBuffer;

/**
 * Represents a vertex buffer object that is represented as a RID in the engine
 *
 * <p>
 * This description is API independent. See the API specific implementations for more information about
 * the usage of the class
 * </p>
 *
 * @author Dorian Ros
 */
public abstract class VertexBuffer {

    /**
     * Unique engine identifier for the object
     */
    protected int RID;

    /**
     * Usage of <code>this</code> vertex buffer as a {@link BufferUsages}
     */
    protected BufferUsages usage;

    /**
     * Create a {@link VertexBuffer} with a specific {@link BufferUsages} that corresponds to
     * the current rendering API
     *
     * @param usage the buffer usage option
     * @return the created vertex buffer
     */
    public static VertexBuffer create(BufferUsages usage) {
        //TODO: switch with context API
        return new GLVertexBuffer(usage);
    }

    /**
     * Set the data stored in <code>this</code> vertex buffer from a {@link FloatBuffer}
     *
     * @param data the float buffer to store in <code>this</code> vertex buffer
     * @return this
     */
    public abstract VertexBuffer setData(FloatBuffer data);

    /**
     * Set the data stored in <code>this</code> vertex buffer from an array of <code>float</code>
     *
     * @param data the float array to store in <code>this</code> vertex buffer
     * @return this
     */
    public abstract VertexBuffer setData(float[] data);

    /**
     * Bind <code>this</code> vertex buffer
     *
     * <p>
     * Call this method before writing or reading from the buffer
     * </p>
     */
    public abstract void bind();

    /**
     * Unbind <code>this</code> vertex buffer
     *
     * <p>
     * You can call this method when there is no need to write or read to the buffer.
     * It is optional and the method won't do anything if the buffer is not currently bound
     * </p>
     */
    public abstract void unbind();

    /**
     * Destroy <code>this</code> vertex buffer
     *
     * <p>
     * Call this method when the buffer is not used anymore and can be deleted
     * </p>
     */
    public abstract void destroy();

    /**
     * Get the {@link #RID} of <code>this</code> vertex buffer
     *
     * @return the RID of this object
     */
    public int RID() {
        return RID;
    }

    @Override
    public String toString() {
        return "VertexBuffer RID: " + RID + ", Usage: " + usage;
    }
}
