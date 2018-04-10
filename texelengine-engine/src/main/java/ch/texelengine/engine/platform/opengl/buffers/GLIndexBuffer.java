package ch.texelengine.engine.platform.opengl.buffers;

import ch.texelengine.engine.api.buffers.BufferUsages;
import ch.texelengine.engine.api.buffers.BufferUtils;
import ch.texelengine.engine.api.buffers.IndexBuffer;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * Represents an OpenGL index buffer object
 *
 * <p>
 * This is the implementation of the {@link IndexBuffer} class for the OpenGL platform
 * </p>
 *
 * @author Dorian Ros
 */
public class GLIndexBuffer extends IndexBuffer {

    /**
     * Construct a new {@link GLIndexBuffer} and gives it a {@link #RID}
     */
    public GLIndexBuffer(BufferUsages usage) {
        this.usage = usage;
        this.RID = glGenBuffers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndexBuffer setData(IntBuffer data) {
        this.count = data.capacity();
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GLBufferUsage.typeOf(this.usage));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndexBuffer setData(int[] data) {
        this.count = data.length;
        bind();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createIntBuffer(data), GLBufferUsage.typeOf(this.usage));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.RID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        glDeleteBuffers(this.RID);
    }
}
