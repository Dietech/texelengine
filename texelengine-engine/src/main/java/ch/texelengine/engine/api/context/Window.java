package ch.texelengine.engine.api.context;

import ch.texelengine.engine.api.buffers.BufferUtils;
import ch.texelengine.engine.api.context.callbacks.*;
import org.lwjgl.glfw.Callbacks;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * This class is a wrapper around an already created context and handles all
 * the window related operations
 *
 * @author Dorian Ros
 */
public class Window {

    /**
     * Pointer to a context object
     */
    private long pointer;

    /**
     * The current width of the window
     */
    private int width;

    /**
     * The current height of the window
     */
    private int height;

    /**
     * The current frame buffer width in pixels
     */
    private int frameWidth;

    /**
     * The current frame buffer height in pixels
     */
    private int frameHeight;

    /**
     * The user defined {@link SizeCallback}
     *
     * <p>
     * Is triggered when the size of the window changed
     * </p>
     */
    private SizeCallback sizeCallback;

    /**
     * The user defined {@link CloseCallback}
     *
     * <p>
     * Is triggered when the window receives a close event
     * </p>
     */
    private CloseCallback closeCallback;

    /**
     * The user defined {@link MinimizeCallback}
     *
     * <p>
     * Is triggered when the window is minimized in the system tray
     * </p>
     */
    private MinimizeCallback minimizeCallback;

    /**
     * The user defined {@link RestoreCallback}
     *
     * <p>
     * Is triggered when the window is restored from the system tray
     * or from a maximized state
     * </p>
     */
    private RestoreCallback restoreCallback;

    /**
     * The user defined framebuffer {@link FrameSizeCallback}
     *
     * <p>
     * Is triggered when the size of the window framebuffer changed
     * </p>
     */
    private FrameSizeCallback frameSizeCallback;

    /**
     * The user defined window {@link PositionCallback}
     *
     * <p>
     * Is triggered when the position on screen of the window changed
     * </p>
     */
    private PositionCallback positionCallback;

    /**
     * The user defined {@link MaximizeCallback}
     *
     * <p>
     * Is triggered when the window is maximized
     * </p>
     */
    private MaximizeCallback maximizeCallback;

    /**
     * The user defined {@link FocusCallback}
     *
     * <p>
     * Is triggered when the window regains focus
     * </p>
     */
    private FocusCallback focusCallback;

    /**
     * The user defined defocus callback as a {@link FocusCallback}
     *
     * <p>
     * Is triggered when the window loses focus
     * </p>
     */
    private FocusCallback defocusCallback;

    /**
     * The user defined {@link RefreshCallback}
     *
     * <p>
     * Is triggered when the window is refreshed
     * </p>
     *
     * @see org.lwjgl.glfw.GLFWWindowRefreshCallback
     */
    private RefreshCallback refreshCallback;

    /**
     * Construct a new {@link Window} and initialize its {@link #pointer} to a
     * created context. Also sets the callbacks for the window
     *
     * @param pointer the pointer to an already created context
     */
    public Window(long pointer) {
        this.pointer = pointer;

        //Get the original width and height of the window and the frame
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(this.pointer, width, height);
        this.width = width.get(0);
        this.height = height.get(0);

        IntBuffer frameWidth = BufferUtils.createIntBuffer(1);
        IntBuffer frameHeight = BufferUtils.createIntBuffer(1);
        glfwGetFramebufferSize(this.pointer, frameWidth, frameHeight);
        this.frameWidth = frameWidth.get(0);
        this.frameHeight = frameHeight.get(0);

        //Sets the window callbacks
        glfwSetWindowSizeCallback(this.pointer, this::sizeCallback);
        glfwSetWindowCloseCallback(this.pointer, this::closeCallback);
        glfwSetWindowIconifyCallback(this.pointer, this::minimizeCallback);
        glfwSetFramebufferSizeCallback(this.pointer, this::frameSizeCallback);
        glfwSetWindowPosCallback(this.pointer, this::positionCallback);
        glfwSetWindowRefreshCallback(this.pointer, this::refreshCallback);
        glfwSetWindowMaximizeCallback(this.pointer, this::maximizeCallback);
        glfwSetWindowFocusCallback(this.pointer, this::focusCallback);
    }

    /**
     * Change the title of <code>this</code> window
     *
     * @param title the new title to set for the window
     */
    public void rename(String title) {
        glfwSetWindowTitle(this.pointer, title);
    }

    /**
     * Resize <code>this</code> window
     *
     * @param width the new width of the window
     * @param height the new height of the window
     */
    public void resize(int width, int height) {
        glfwSetWindowSize(this.pointer, width, height);
    }

    /**
     * Minimize <code>this</code> window in the system tray
     */
    public void minimize() {
        glfwIconifyWindow(this.pointer);
    }

    /**
     * Force the focus on <code>this</code> window
     */
    public void focus() {
        glfwFocusWindow(this.pointer);
    }

    /**
     * Maximize <code>this</code> window
     */
    public void maximize() {
        glfwMaximizeWindow(this.pointer);
    }

    /**
     * Restore <code>this</code> window from either a minimized or a maximized state
     */
    public void restore() {
        glfwRestoreWindow(this.pointer);
    }

    /**
     * Hides <code>this</code> window
     *
     * <p>
     * After calling this method, the window will be completely hidden to the user.
     * This includes that the icon will not be visible in the system tray.
     * It can only be restored using the {@link #show()} method.
     * </p>
     */
    public void hide() {
        glfwHideWindow(this.pointer);
    }

    /**
     * Restore <code>this</code> window from a hidden state
     *
     * @see #hide()
     */
    public void show() {
        glfwShowWindow(this.pointer);
    }

    /**
     * Set the close flag for <code>this</code> window
     */
    public void close() {
        glfwSetWindowShouldClose(this.pointer, true);
    }

    /**
     * Get the flag that defines if <code>this</code> window received a close event
     *
     * @return the close flag for the window
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(this.pointer);
    }

    /**
     * Get the current width of <code>this</code> window in screen coordinates
     *
     * @return the current width of the window
     */
    public int width() {
        return width;
    }

    /**
     * Get the current height of <code>this</code> window in screen coordinates
     *
     * @return the current height of the window
     */
    public int height() {
        return height;
    }

    /**
     * Get the current framebuffer width of <code>this</code> window in pixels
     *
     * @return the current framebuffer width of the window
     */
    public int frameWidth() {
        return frameWidth;
    }

    /**
     * Get the current framebuffer height of <code>this</code> window in pixels
     *
     * @return the current framebuffer height of the window
     */
    public int frameHeight() {
        return frameHeight;
    }

    /**
     * Swap the front and back buffer
     *
     * <p>
     * Call this method once per frame once the rendering part is done and you want
     * to display it on the screen.
     * </p>
     */
    public void swapBuffers() {
        glfwSwapBuffers(this.pointer);
    }

    /**
     * Processes the pending window events
     *
     * <p>
     * Call this method once per frame.
     * </p>
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Set the resize callback for <code>this</code> window
     *
     * @param callback the {@link SizeCallback} for the window
     */
    public void onResize(SizeCallback callback) {
        this.sizeCallback = callback;
    }

    /**
     * Set the close callback for <code>this</code> window
     *
     * @param callback the {@link CloseCallback} for the window
     */
    public void onClose(CloseCallback callback) {
        this.closeCallback = callback;
    }

    /**
     * Set the minimize callback for <code>this</code> window
     *
     * @param callback the {@link MinimizeCallback} for the window
     */
    public void onMinimize(MinimizeCallback callback) {
        this.minimizeCallback = callback;
    }

    /**
     * Set the restore callback for <code>this</code> window
     *
     * @param callback the {@link RestoreCallback} for the window
     */
    public void onRestored(RestoreCallback callback) {
        this.restoreCallback = callback;
    }

    /**
     * Set the framebuffer size callback for <code>this</code> window
     *
     * @param callback the {@link FrameSizeCallback} for the window
     */
    public void onFrameResize(FrameSizeCallback callback) {
        this.frameSizeCallback = callback;
    }

    /**
     * Set the position callback for <code>this</code> window
     *
     * @param callback the {@link PositionCallback} for the window
     */
    public void onMove(PositionCallback callback) {
        this.positionCallback = callback;
    }

    /**
     * Set the refresh callback for <code>this</code> window
     *
     * @param callback the {@link RefreshCallback} for the window
     */
    public void onRefresh(RefreshCallback callback) {
        this.refreshCallback = callback;
    }

    /**
     * Set the maximize callback for <code>this</code> window
     *
     * @param callback the {@link MaximizeCallback} for the window
     */
    public void onMaximize(MaximizeCallback callback) {
        this.maximizeCallback = callback;
    }

    /**
     * Set the focus callback for <code>this</code> window
     *
     * @param callback the {@link FocusCallback} for the window
     */
    public void onFocus(FocusCallback callback) {
        this.focusCallback = callback;
    }

    /**
     * Set the defocus callback for <code>this</code> window
     *
     * @param callback the {@link FocusCallback} for the window
     */
    public void onDefocus(FocusCallback callback) {
        this.defocusCallback = callback;
    }

    /**
     * Destroy the window and free the set callbacks
     */
    public void destroy() {
        Callbacks.glfwFreeCallbacks(this.pointer);
    }

    /**
     * Size callback method for <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     * @param width the new width of the window in screen coordinates
     * @param height the new height of the window in screen coordinates
     *
     * @see org.lwjgl.glfw.GLFWWindowSizeCallback
     */
    private void sizeCallback(long window, int width, int height) {
        this.width = width;
        this.height = height;

        //Triggers the user-defined callback
        if(this.sizeCallback != null) {
            this.sizeCallback.invoke(width, height);
        }
    }

    /**
     * Close callback method for <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     *
     * @see org.lwjgl.glfw.GLFWWindowCloseCallback
     */
    private void closeCallback(long window) {

        //Triggers the user-defined callback
        if(this.closeCallback != null) {
            this.closeCallback.invoke();
        }
    }

    /**
     * Minimize callback method for <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     * @param minimized the flag that defines whether the window was minimized or restored
     *
     * @see org.lwjgl.glfw.GLFWWindowIconifyCallback
     */
    private void minimizeCallback(long window, boolean minimized) {

        //Triggers the user-defined callback
        if(minimized) {
            if(this.minimizeCallback != null) {
                this.minimizeCallback.invoke();
            }
        } else {
            if(this.restoreCallback != null) {
                this.restoreCallback.invoke();
            }
        }
    }

    /**
     * Framebuffer size callback method for <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     * @param width the new width of the framebuffer in pixels
     * @param height the new height of the framebuffer in pixels
     *
     * @see org.lwjgl.glfw.GLFWFramebufferSizeCallback
     */
    private void frameSizeCallback(long window, int width, int height) {
        this.frameWidth = width;
        this.frameHeight = height;

        //Triggers the user-defined callback
        if(this.frameSizeCallback != null) {
            this.frameSizeCallback.invoke(width, height);
        }
    }

    /**
     * Position callback method for <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     * @param x the new x position of the window in screen coordinates
     * @param y the new y position of the window in screen coordinates
     *
     * @see org.lwjgl.glfw.GLFWWindowPosCallback
     */
    private void positionCallback(long window, int x, int y) {

        //Triggers the user-defined callback
        if(this.positionCallback != null) {
            this.positionCallback.invoke(x, y);
        }
    }

    /**
     * Refresh callback method for <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     *
     * @see org.lwjgl.glfw.GLFWWindowRefreshCallback
     */
    private void refreshCallback(long window) {

        //Triggers the user-defined callback
        if(this.refreshCallback != null) {
            this.refreshCallback.invoke();
        }
    }

    /**
     * Maximize callback method for <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     * @param maximized the flag that defines whether the window was maximized or restored
     *
     * @see org.lwjgl.glfw.GLFWWindowMaximizeCallback
     */
    private void maximizeCallback(long window, boolean maximized) {

        //Triggers the user-defined callback
        if(maximized) {
            if(this.maximizeCallback != null) {
                this.maximizeCallback.invoke();
            }
        } else {
            if(this.restoreCallback != null) {
                this.restoreCallback.invoke();
            }
        }
    }

    /**
     * Focus callback method rof <code>this</code> window
     *
     * @param window the pointer to the GLFW context
     * @param focused the flag that defines whether the window was focused or defocused
     *
     * @see org.lwjgl.glfw.GLFWWindowFocusCallback
     */
    private void focusCallback(long window, boolean focused) {

        //Triggers the user-defined callback
        if(focused) {
            if(this.focusCallback != null) {
                this.focusCallback.invoke();
            }
        } else {
            if(this.defocusCallback != null) {
                this.defocusCallback.invoke();
            }
        }
    }
}
