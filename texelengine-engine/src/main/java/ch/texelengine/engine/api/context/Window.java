package ch.texelengine.engine.api.context;

import ch.texelengine.engine.api.buffers.BufferUtils;
import ch.texelengine.engine.api.context.callbacks.CloseCallback;
import ch.texelengine.engine.api.context.callbacks.SizeCallback;
import ch.texelengine.engine.api.context.callbacks.WindowCallback;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.Callback;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

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

    private int width;

    private int height;

    private int frameWidth;

    private int frameHeight;

    private SizeCallback sizeCallback;

    private CloseCallback closeCallback;

    /**
     * Construct a new {@link Window} and initialize its {@link #pointer} to a
     * created context.
     *
     * @param pointer the pointer to an allready created context
     */
    public Window(long pointer) {
        this.pointer = pointer;

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

        glfwSetWindowSizeCallback(this.pointer, this::sizeCallback);
        glfwSetWindowCloseCallback(this.pointer, this::closeCallback);
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
            sizeCallback.invoke(width, height);
        }
    }

    /**
     *
     * @param window
     */
    private void closeCallback(long window) {

        //Triggers the user-defined callback
        if(this.closeCallback != null) {
            closeCallback.invoke();
        }
    }

}
