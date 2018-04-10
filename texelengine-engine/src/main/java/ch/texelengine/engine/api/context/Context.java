package ch.texelengine.engine.api.context;

import ch.texelengine.engine.platform.opengl.context.GLContext;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Represents a graphical context that has a pointer
 *
 * <p>
 * As the engine uses GLFW, this class creates a GLFW window that will be passed to the window class to initialize its
 * parameters (size, title, etc...)
 * </p>
 *
 * <p>
 * This description is API independent. See the API specific implementations for more information about
 * the usage of the class
 * </p>
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
     * Store whether GLFW has been successfully initialized
     */
    private static boolean glfwInitialized;

    /**
     * Create a {@link Context} for a specific {@link GraphicsAPI} and make it the current context
     *
     * @param api the graphics API to use
     *
     * @throws RuntimeException initialization process fails
     *
     * @return the created context
     */
    public static Context create(GraphicsAPI api, ContextParameters parameters) throws RuntimeException {

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
                context = new GLContext(parameters);
                break;
            default:
                context = null;
                break;
        }

        //Check if the context is correctly created
        if (context == null || context.pointer == NULL) {
            throw new RuntimeException("Failed to create context");
        }

        //Make the created context the current context
        context.makeCurrent();

        //Initializes the context
        context.init();

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
     * Initialize the context after creation
     */
    protected abstract void init();

    /**
     * Destroy <code>this</code> context
     */
    public abstract void destroyContext();

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
     * Get the {@link GraphicsAPI} of <code>this</code> context
     *
     * @return the api of the context
     */
    public GraphicsAPI api() {
        return this.api;
    }
}
