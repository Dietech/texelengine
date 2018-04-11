package ch.texelengine.engine.platform.opengl.context;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import ch.texelengine.engine.api.context.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import java.util.Objects;

/**
 * Describe a context for the OpenGL graphics API
 *
 * <p>
 * This is the implementation of the {@link Context} class for the OpenGL platform
 * </p>
 *
 * @author Dorian Ros
 */
public class GLContext extends Context {

    /**
     * Construct a new graphical {@link Context} object and the associated {@link Window}
     * object. The created context is automatically made current and is initialized.
     * The {@link #api} is also set to ({@link GraphicsAPI#OPENGL})
     *
     * <p>
     * This constructor does NOT initialize {@link GLFW} that is required to create the context/window.
     * It is better to use the generic {@link Context#create(GraphicsAPI, ContextParameters, WindowParameters)}
     * </p>
     */
    public GLContext(ContextParameters contextParams, WindowParameters windowParams) {
        this.api = GraphicsAPI.OPENGL;

        Objects.requireNonNull(contextParams);

        //Set context related parameters
        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, contextParams.major());
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, contextParams.minor());
        glfwWindowHint(GLFW_CONTEXT_NO_ERROR, contextParams.noErrors() ? GLFW_TRUE : GLFW_FALSE);

        //Set OpenGL related parameters
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        //Set the window visibility to false
        //The window will be shown later in the initialization
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        this.makeCurrent();

        this.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init() {
        //Load the OpenGL natives and the needed extensions
        GL.createCapabilities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyContext() {
        glfwDestroyWindow(this.pointer);
    }
}
