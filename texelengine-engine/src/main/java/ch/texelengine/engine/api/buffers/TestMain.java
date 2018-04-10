package ch.texelengine.engine.api.buffers;

import ch.texelengine.engine.api.context.Context;
import ch.texelengine.engine.api.context.ContextParameters;
import ch.texelengine.engine.api.context.GraphicsAPI;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;

/**
 * @author Dorian Ros
 */
public class TestMain {
    public static void main(String[] args) {
        Context context = Context.create(GraphicsAPI.OPENGL, new ContextParameters(3, 3, false));


    }
}
