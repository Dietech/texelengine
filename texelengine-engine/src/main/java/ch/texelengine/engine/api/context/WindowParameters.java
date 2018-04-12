package ch.texelengine.engine.api.context;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Describe a parameter set used to initialize a window in GLFW
 *
 * @author Dorian Ros
 */
public class WindowParameters {

    /**
     * Predefined {@link WindowParameters} that describes an invisible window. Useful if you want to work with a
     * context without a window
     */
    public final static WindowParameters NONE = new WindowParameters(1, 1, "Texel Engine", false, false, false, false);

    /**
     * Predefined {@link WindowParameters} that describes a default 16:9 1280x720 VSynced resizable window.
     */
    public final static WindowParameters DEFAULT = new WindowParameters(1280, 720, "Texel Engine", false, true, true, true);

    /**
     * Predefined {@link WindowParameters} that describes a fullscreen window with the resolution of the screen.
     */
    public final static WindowParameters FULLSCREEN = new WindowParameters(0, 0, "Texel Engine", true, true, false, true);

    /**
     * The width of the window, generally in pixels
     */
    private final int width;

    /**
     * The height of the window, generally in pixels
     */
    private final int height;

    /**
     * The title that is displayed at the top of the window
     */
    private final String title;

    /**
     * A flag that defines if the window is fullscreen
     */
    private final boolean fullscreen;

    /**
     * A flag that defines if the window use VSync
     */
    private final boolean vsync;

    /**
     * A flag that defines if the window is resizable
     */
    private final boolean resizable;

    /**
     * A flag that defines if the window can be shown on the screen
     */
    private final boolean visible;

    /**
     * Construct a new {@link WindowParameters} object and initialize all of its parameters
     *
     * <p>
     * This constructor is made private to not lead the user into conflicting. Instead, this class provides default
     * static instances of the class with default parameters for different scenarios.
     * </p>
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title that shows up at the top of the window
     * @param fullscreen a flag that defines if the window is fullscreen
     * @param vsync a flag that defines if the window use VSync
     * @param resizable a flag that defines that defines if the window is resizable
     * @param visible a flag that defines if the window can be shown on the screen
     */
    private WindowParameters(int width, int height, String title, boolean fullscreen, boolean vsync, boolean resizable, boolean visible) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.fullscreen = fullscreen;
        this.vsync = vsync;
        this.resizable = resizable;
        this.visible = visible;
    }

    /**
     * Constructs a new {@link WindowParameters} object
     *
     * <p>
     * The rest of the parameters are set as the following:
     * <br />
     * <ul>
     *     <li>resizable: <code>false</code></li>
     *     <li>visible: <code>true</code></li>
     * </ul>
     * </p>
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title that shows up at the top of the window
     * @param fullscreen a flag that defines if the window is fullscreen
     * @param vsync a flag that defines if the window use VSync
     */
    public WindowParameters(int width, int height, String title, boolean fullscreen, boolean vsync) {
        this(width, height, title, fullscreen, vsync, false, true);
    }

    /**
     * Constructs a new {@link WindowParameters} object
     *
     * <p>
     * The rest of the parameters are set as the following:
     * <br />
     * <ul>
     *     <li>fullscreen: <code>false</code></li>
     *     <li>resizable: <code>true</code></li>
     *     <li>visible: <code>true</code></li>
     * </ul>
     * </p>
     *
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title that shows up at the top of the window
     * @param vsync a flag that defines if the window use VSync
     */
    public WindowParameters(int width, int height, String title, boolean vsync) {
        this(width, height, title, false, vsync, true, true);
    }

    /**
     * Get the {@link #width} of the window parameters
     *
     * @return the width
     */
    public int width() {
        return width;
    }

    /**
     * Get the {@link #height} of the window parameters
     *
     * @return the height
     */
    public int height() {
        return height;
    }

    /**
     * Get the {@link #title} of the window parameters
     *
     * @return the title
     */
    public String title() {
        return title;
    }

    /**
     * Get the {@link #fullscreen} flag for the window parameters
     *
     * @return the fullscreen flag in the default GLFW format
     */
    public int fullscreen() {
        return fullscreen ? GLFW_TRUE : GLFW_FALSE;
    }

    /**
     * Get the {@link #vsync} flag for the window parameters
     *
     * @return the vsync flag in the default GLFW format
     */
    public int vsync() {
        return vsync ? GLFW_TRUE : GLFW_FALSE;
    }

    /**
     * Get the {@link #resizable} flag for the window parameters
     *
     * @return the resizable flag in the default GLFW format
     */
    public int resizable() {
        return resizable ? GLFW_TRUE : GLFW_FALSE;
    }

    /**
     * Get the {@link #visible} flag for the window parameters
     *
     * @return the visible flag in the default GLFW format
     */
    public int visible() {
        return visible ? GLFW_TRUE : GLFW_FALSE;
    }
}
