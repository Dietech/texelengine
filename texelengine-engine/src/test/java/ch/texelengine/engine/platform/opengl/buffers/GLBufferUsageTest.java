package ch.texelengine.engine.platform.opengl.buffers;

import ch.texelengine.engine.api.buffers.BufferUsages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.lwjgl.opengl.GL11.GL_NONE;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

class GLBufferUsageTest {

    @Test
    public void asGLBufferUsageReturnsCorrectValues() {
        assertEquals(GLBufferUsage.typeOf(BufferUsages.STATIC), GL_STATIC_DRAW);
        assertEquals(GLBufferUsage.typeOf(BufferUsages.DYNAMIC), GL_DYNAMIC_DRAW);
    }

    @Test
    public void asGLBufferUsageReturnsNoneIfNull() {
        assertEquals(GLBufferUsage.typeOf(null), GL_NONE);
    }
}