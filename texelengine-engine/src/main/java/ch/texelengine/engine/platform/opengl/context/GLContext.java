package ch.texelengine.engine.platform.opengl.context;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import ch.texelengine.engine.api.context.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
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
     *
     * @throws RuntimeException the the context initialization failed
     */
    public GLContext(ContextParameters contextParams, WindowParameters windowParams) throws RuntimeException {
        this.api = GraphicsAPI.OPENGL;

        Objects.requireNonNull(contextParams);
        Objects.requireNonNull(windowParams);

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

        //Set window related parameters
        glfwWindowHint(GLFW_RESIZABLE, windowParams.resizable());
        glfwWindowHint(GLFW_REFRESH_RATE, GLFW_DONT_CARE);

        int width = windowParams.width();
        int height = windowParams.height();

        //Get the video mode for the main screen
        final GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        if (mode == null) {
            throw new RuntimeException("Failed to get the primary monitor");
        }

        long monitor = NULL;
        if(windowParams.fullscreen() == GLFW_TRUE) {
            monitor = glfwGetPrimaryMonitor();
            if(monitor == NULL) {
                throw new RuntimeException("Failed to get the primary monitor");
            }
            //If the width and height is set to zero that means the window takes
            //the dimension of the main screen
            if(width == 0 && height == 0) {
                width = mode.width();
                height = mode.height();
                glfwWindowHint(GLFW_REFRESH_RATE, mode.refreshRate());
            }
        }

        //Creation of the context and window
        this.pointer = glfwCreateWindow(width, height, windowParams.title(), monitor, NULL);
        if(this.pointer == NULL) {
            throw new RuntimeException("Failed to create context");
        }

        //Creates the underlying window object
        this.window = new Window(this.pointer);

        //Centers the window if not fullscreen
        if(windowParams.fullscreen() != GLFW_TRUE) {
            glfwSetWindowPos(this.pointer, (mode.width() - width) / 2, (mode.height() - height) / 2);
        }

        //Set VSync and window visibility
        glfwSwapInterval(windowParams.vsync());
        if(windowParams.visible() == GLFW_TRUE) {
            glfwShowWindow(this.pointer);
        }

        //Make the context current
        this.makeCurrent();

        //Loads the OpenGL natives
        GL.createCapabilities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyContext() {
        this.window.destroy();
        glfwDestroyWindow(this.pointer);
        this.pointer = NULL;
    }
}
