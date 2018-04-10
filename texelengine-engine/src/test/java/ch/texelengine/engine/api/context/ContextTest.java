package ch.texelengine.engine.api.context;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL;

import static org.junit.jupiter.api.Assertions.*;
import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

class ContextTest {

    @Test
    public void ContextThrowsNullPointerIfAPINull() {
        assertThrows(NullPointerException.class, () -> Context.create(null, new ContextParameters(1, 1)));
        assertThrows(NullPointerException.class, () -> Context.create(GraphicsAPI.OPENGL, null));
        assertThrows(NullPointerException.class, () -> Context.create(null, null));
    }

    @Test
    public void MakeCurrentWorks() {
        Context context = Context.create(GraphicsAPI.OPENGL, new ContextParameters(3, 3));
        Context context2 = Context.create(GraphicsAPI.OPENGL, new ContextParameters(3, 3));
        context.makeCurrent();
        assertEquals(context.pointer, glfwGetCurrentContext());
        context2.makeCurrent();
        assertEquals(context2.pointer, glfwGetCurrentContext());
    }

    @Test
    public void DestroyContextWorks() {
        Context context = Context.create(GraphicsAPI.OPENGL, new ContextParameters(3, 3));
        assertEquals(context.pointer, glfwGetCurrentContext());
        context.destroyContext();
        assertEquals(0, glfwGetCurrentContext());
    }
}