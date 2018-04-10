package ch.texelengine.engine.api.buffers;

import ch.texelengine.engine.platform.opengl.buffers.GLVertexArray;

/**
 * Represents a vertex array object that is represented as a RID in the engine
 *
 * <p>
 * This description is API independent. See the API specific implementations for more information about
 * the usage of the class
 * </p>
 *
 * @see ch.texelengine.engine.platform.opengl.buffers.GLVertexArray
 *
 * @author Dorian Ros
 */
public abstract class VertexArray {

    /**
     * Unique engine identifier for the object
     */
    protected int RID;

    /**
     * Create a {@link VertexArray} that corresponds to the current graphics API
     *
     * @return the created vertex array
     */
    public static VertexArray create() {
        //TODO: switch with context API
        return new GLVertexArray();
    }

    /**
     * Add a {@link VertexBuffer} with a specific {@link VertexBufferLayout} to <code>this</code> vertex array
     *
     * @param buffer the buffer to bind
     * @param layout the layout of the buffer to bind
     * @return this
     */
    public abstract VertexArray pushBuffer(VertexBuffer buffer, VertexBufferLayout layout);

    /**
     * Bind <code>this</code> vertex array
     *
     * <p>
     * Call this method before writing or reading from the buffer
     * </p>
     */
    public abstract void bind();

    /**
     * Unbind <code>this</code> vertex array
     *
     * <p>
     * You can call this method when there is no need to write or read to the buffer.
     * It is optional and the method won't do anything if the buffer is not currently bound
     * </p>
     */
    public abstract void unbind();

    /**
     * Destroy <code>this</code> vertex array
     *
     * <p>
     * Call this method when the buffer is not used anymore and can be deleted
     * </p>
     */
    public abstract void destroy();

    /**
     * Get the {@link #RID} of <code>this</code> vertex array
     *
     * @return the RID of this object
     */
    public int RID() {
        return RID;
    }

    @Override
    public String toString() {
        return "VertexArray RID: " + RID;
    }
}
