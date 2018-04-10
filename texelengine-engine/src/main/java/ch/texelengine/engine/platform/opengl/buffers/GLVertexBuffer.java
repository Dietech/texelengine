package ch.texelengine.engine.platform.opengl.buffers;

import ch.texelengine.engine.api.buffers.BufferUsages;
import ch.texelengine.engine.api.buffers.BufferUtils;
import ch.texelengine.engine.api.buffers.VertexBuffer;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * Represents an OpenGL vertex buffer object
 *
 * <p>
 * This is the implementation of the {@link VertexBuffer} class for the OpenGL platform
 * </p>
 *
 * @author Dorian Ros
 */
public class GLVertexBuffer extends VertexBuffer {

    /**
     * Construct a new {@link GLVertexBuffer} with a defined {@link BufferUsages} and gives it a {@link #RID}
     *
     * @param usage the usage of the buffer
     */
    public GLVertexBuffer(BufferUsages usage) {
        this.RID = glGenBuffers();
        this.usage = usage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexBuffer setData(FloatBuffer data) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, data, GLBufferUsage.typeOf(this.usage));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexBuffer setData(float[] data) {
        bind();
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(data), GLBufferUsage.typeOf(this.usage));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.RID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        glDeleteBuffers(this.RID);
    }
}
