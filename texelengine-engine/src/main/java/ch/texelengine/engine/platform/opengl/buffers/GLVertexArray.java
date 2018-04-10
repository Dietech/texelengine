package ch.texelengine.engine.platform.opengl.buffers;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;

import ch.texelengine.engine.api.buffers.VertexArray;
import ch.texelengine.engine.api.buffers.VertexBuffer;
import ch.texelengine.engine.api.buffers.VertexBufferElement;
import ch.texelengine.engine.api.buffers.VertexBufferLayout;

import java.util.List;

/**
 * Represents an OpenGL vertex array object
 *
 * <p>
 * This is the implementation of the {@link VertexArray} class for the OpenGL platform
 * </p>
 *
 * @author Dorian Ros
 */
public class GLVertexArray extends VertexArray {

    /**
     * Construct a new {@link GLVertexArray} and gives it a {@link #RID}
     */
    public GLVertexArray() {
        this.RID = glGenVertexArrays();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexArray pushBuffer(VertexBuffer buffer, VertexBufferLayout layout) {
        bind();
        buffer.bind();
        List<VertexBufferElement> elements = layout.layout();
        int offset = 0;
        for (int i = 0; i < elements.size(); i++) {
            VertexBufferElement element = elements.get(i);
            glEnableVertexAttribArray(i);
            glVertexAttribPointer(i, element.count(), GLDataType.typeOf(element.type()), element.normalised(), layout.stride(), offset);
            offset += element.count() * GLDataType.sizeOf(element.type());
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        glBindVertexArray(this.RID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        glBindVertexArray(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        glDeleteVertexArrays(this.RID);
    }
}
