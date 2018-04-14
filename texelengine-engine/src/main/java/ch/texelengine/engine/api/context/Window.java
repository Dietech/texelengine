package ch.texelengine.engine.api.context;

import ch.texelengine.engine.api.buffers.BufferUtils;
import ch.texelengine.engine.api.context.callbacks.*;
import org.lwjgl.glfw.Callbacks;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * This class is a wrapper around an allready created context and handles all
 * the window related operations
 *
 * @author Dorian Ros
 */
public class Window {

    /**
     * Pointer to a context object
     */
    protected long pointer;

    /**
     *
     */
    private int width;

    /**
     *
     */
    private int height;

    /**
     *
     */
    private int frameWidth;

    /**
     *
     */
    private int frameHeight;

    /**
     *
     */
    private SizeCallback sizeCallback;

    /**
     *
     */
    private CloseCallback closeCallback;

    /**
     *
     */
    private MinimizeCallback minimizeCallback;

    /**
     *
     */
    private RestoreCallback restoreCallback;

    /**
     *
     */
    private FrameSizeCallback frameSizeCallback;

    /**
     *
     */
    private PositionCallback positionCallback;

    /**
     *
     */
    private MaximizeCallback maximizeCallback;

    /**
     *
     */
    private FocusCallback focusCallback;

    /**
     *
     */
    private FocusCallback defocusCallback;

    /**
     *
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
     *
     * @param title
     */
    public void rename(String title) {
        glfwSetWindowTitle(this.pointer, title);
    }

    /**
     *
     * @param width
     * @param height
     */
    public void resize(int width, int height) {
        glfwSetWindowSize(this.pointer, width, height);
    }

    /**
     *
     */
    public void minimize() {
        glfwIconifyWindow(this.pointer);
    }

    /**
     *
     */
    public void focus() {
        glfwFocusWindow(this.pointer);
    }

    /**
     *
     */
    public void maximize() {
        glfwMaximizeWindow(this.pointer);
    }

    /**
     *
     */
    public void restore() {
        glfwRestoreWindow(this.pointer);
    }

    /**
     *
     */
    public void hide() {
        glfwHideWindow(this.pointer);
    }

    /**
     *
     */
    public void show() {
        glfwShowWindow(this.pointer);
    }

    /**
     *
     * @return
     */
    public int width() {
        return width;
    }

    /**
     *
     * @return
     */
    public int height() {
        return height;
    }

    /**
     *
     * @return
     */
    public int frameWidth() {
        return frameWidth;
    }

    /**
     *
     * @return
     */
    public int frameHeight() {
        return frameHeight;
    }

    /**
     *
     */
    public void swapBuffers() {
        glfwSwapBuffers(this.pointer);
    }

    /**
     *
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     *
     * @param callback
     */
    public void onResize(SizeCallback callback) {
        this.sizeCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onClose(CloseCallback callback) {
        this.closeCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onMinimize(MinimizeCallback callback) {
        this.minimizeCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onRestored(RestoreCallback callback) {
        this.restoreCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onFrameResize(FrameSizeCallback callback) {
        this.frameSizeCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onMove(PositionCallback callback) {
        this.positionCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onRefresh(RefreshCallback callback) {
        this.refreshCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onMaximize(MaximizeCallback callback) {
        this.maximizeCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onFocus(FocusCallback callback) {
        this.focusCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onDefocus(FocusCallback callback) {
        this.defocusCallback = callback;
    }

    /**
     *
     */
    public void destroy() {
        Callbacks.glfwFreeCallbacks(this.pointer);
    }

    /**
     *
     *
     * @param window
     * @param width
     * @param height
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
     *
     * @param window
     */
    private void closeCallback(long window) {

        //Triggers the user-defined callback
        if(this.closeCallback != null) {
            this.closeCallback.invoke();
        }
    }

    /**
     *
     * @param window
     * @param minimized
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
     *
     * @param window
     * @param width
     * @param height
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
     *
     * @param window
     * @param x
     * @param y
     */
    private void positionCallback(long window, int x, int y) {

        //Triggers the user-defined callback
        if(this.positionCallback != null) {
            this.positionCallback.invoke(x, y);
        }
    }

    /**
     *
     * @param window
     */
    private void refreshCallback(long window) {

        //Triggers the user-defined callback
        if(this.refreshCallback != null) {
            this.refreshCallback.invoke();
        }
    }

    /**
     *
     * @param window
     * @param maximized
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
     *
     * @param window
     * @param focused
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
