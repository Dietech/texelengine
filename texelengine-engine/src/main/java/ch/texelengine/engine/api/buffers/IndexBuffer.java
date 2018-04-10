package ch.texelengine.engine.api.buffers;

import ch.texelengine.engine.platform.opengl.buffers.GLIndexBuffer;

import java.nio.IntBuffer;

/**
 * Represents an index buffer object that is represented as a RID in the engine
 *
 * <p>
 * This description is API independent. See the API specific implementations for more information about
 * the usage of the class
 * </p>
 *
 * @author Dorian Ros
 */
public abstract class IndexBuffer {

    /**
     * Unique engine identifier for the object
     */
    protected int RID;

    /**
     * Number of indexes in the buffer
     */
    protected int count;

    /**
     * Usage of <code>this</code> index buffer as a {@link BufferUsages}
     */
    protected BufferUsages usage;

    /**
     * Create an empty {@link IndexBuffer}. The object corresponds to the current API
     *
     * @return the created index buffer
     */
    public static IndexBuffer create(BufferUsages usage) {
        //TODO: switch with context API
        return new GLIndexBuffer(usage);
    }

    /**
     * Set the data stored in <code>this</code> index buffer from an {@link IntBuffer}
     *
     * @param data the int buffer to store in <code>this</code> index buffer
     * @return this
     */
    public abstract IndexBuffer setData(IntBuffer data);

    /**
     * Set the data stored in <code>this</code> index buffer from an int array
     *
     * @param data the int array to store in <code>this</code> index buffer
     * @return this
     */
    public abstract IndexBuffer setData(int[] data);

    /**
     * Bind <code>this</code> index buffer
     *
     * <p>
     * Call this method before writing or reading from the buffer
     * </p>
     */
    public abstract void bind();

    /**
     * Unbind <code>this</code> index buffer
     *
     * <p>
     * You can call this method when there is no need to write or read to the buffer.
     * It is optional and the method won't do anything if the buffer is not currently bound
     * </p>
     */
    public abstract void unbind();

    /**
     * Destroy <code>this</code> index buffer
     *
     * <p>
     * Call this method when the buffer is not used anymore and can be deleted
     * </p>
     */
    public abstract void destroy();

    /**
     * Get the {@link #RID} of <code>this</code> index buffer
     *
     * @return the RID of this object
     */
    public int RID() {
        return RID;
    }

    /**
     * Get the {@link #count} of <code>this</code> index buffer
     *
     * @return the number of indexes in the buffer
     */
    public int count() { return this.count; }

    @Override
    public String toString() {
        return "IndexBuffer RID: " + RID + ", count: " + count;
    }
}
