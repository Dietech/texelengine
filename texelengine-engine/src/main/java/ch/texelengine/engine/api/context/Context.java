package ch.texelengine.engine.api.context;

import ch.texelengine.engine.platform.opengl.context.GLContext;

import java.util.Objects;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Represents a graphical context that has a pointer
 *
 * <p>
 * As the engine uses GLFW, the context and the window are the same object. Therefore, the context has a
 * {@link Window} parameter that handles the window functions. This window is created at
 * the same time as the context.
 * </p>
 *
 * <p>
 * This description is API independent. See the API specific implementations for more information about
 * the usage of the class
 * </p>
 *
 * @see GLContext
 *
 * @author Dorian Ros
 */
public abstract class Context {

    /**
     * Pointer to the glfw window/context object
     */
    protected long pointer;

    /**
     * API used to create the context
     */
    protected GraphicsAPI api;

    /**
     * {@link Window} object attached to <code>this</code> context
     */
    protected Window window;

    /**
     * Store whether GLFW has been successfully initialized
     */
    private static boolean glfwInitialized;

    /**
     * Create a {@link Context} for a specific {@link GraphicsAPI} and make it the current context
     *
     * @param api the graphics API to use
     *
     * @throws IllegalArgumentException the graphics API parameter is invalid
     * @throws RuntimeException the context creation failed
     *
     * @return the created context
     */
    public static Context create(GraphicsAPI api, ContextParameters contextParams, WindowParameters windowParams)
            throws IllegalArgumentException, RuntimeException {

        //If glfw has not been initialized then do it
        if(!glfwInitialized) {
            glfwInitialized = glfwInit();

            //Check if GLFW is correctly initialized
            if(!glfwInitialized) {
                throw new RuntimeException("Failed to initialize GLFW");
            }
        }

        //Context creation
        Objects.requireNonNull(api);
        Context context;
        switch(api) {
            case OPENGL:
                context = new GLContext(contextParams, windowParams);
                break;
            default:
                context = null;
                break;
        }

        //Check if the context is valid
        if (context == null) {
            throw new IllegalArgumentException("Invalid API parameter");
        }

        return context;
    }

    /**
     * Destroy the remaining contexts and windows
     */
    public static void destroy() {
        glfwTerminate();
        glfwInitialized = false;
    }

    /**
     * Make <code>this</code> context the current context
     *
     * <p>
     * It is required to make the context current to make API calls
     * </p>
     */
    public void makeCurrent() {
        glfwMakeContextCurrent(this.pointer);
    }

    /**
     * Destroy <code>this</code> context.
     *
     * <p>
     * The {@link #pointer} is set to NULL
     * </p>
     */
    public abstract void destroyContext();

    /**
     * Get the {@link GraphicsAPI} of <code>this</code> context
     *
     * @return the api of the context
     */
    public GraphicsAPI api() {
        return this.api;
    }

    /**
     * Get the {@link Window} attached to <code>this</code> context
     *
     * @return the window object
     */
    public Window window() {
        return this.window;
    }
}
