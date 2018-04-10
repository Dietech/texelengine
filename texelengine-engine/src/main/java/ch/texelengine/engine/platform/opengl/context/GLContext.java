package ch.texelengine.engine.platform.opengl.context;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import ch.texelengine.engine.api.context.Context;
import ch.texelengine.engine.api.context.ContextParameters;
import ch.texelengine.engine.api.context.GraphicsAPI;
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
     * Construct a new {@link GLContext} and assign the {@link #pointer} variable.
     * The {@link #api} is also set to the correct value ({@link GraphicsAPI#OPENGL})
     *
     * <p>
     * In order for the context to be usable it needs to be made current and to be
     * initialized
     * </p>
     */
    public GLContext(ContextParameters parameters) {
        this.api = GraphicsAPI.OPENGL;

        Objects.requireNonNull(parameters);

        //Set context related parameters
        glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, parameters.major());
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, parameters.minor());
        glfwWindowHint(GLFW_CONTEXT_NO_ERROR, parameters.noErrors() ? GLFW_TRUE : GLFW_FALSE);

        //Set OpenGL related parameters
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        //Set the window visibility to false
        //The window will be shown later during the window initialization
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        //Create the window/context with a default 200x200 size and no title
        //These parameters will be changed later during the window initialization
        this.pointer = glfwCreateWindow(200, 200, "", NULL, NULL);
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
