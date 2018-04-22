package ch.texelengine.engine.api.context;

import ch.texelengine.engine.api.context.callbacks.CursorEnterCallback;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author Dorian Ros
 */
public class Mouse {

    /**
     *
     */
    private long pointer;

    /**
     *
     */
    private boolean[] buttonChanged;

    /**
     *
     */
    private int[] buttons;

    /**
     *
     */
    private CursorEnterCallback cursorEnterCallback;

    /**
     *
     */
    private CursorEnterCallback cursorLeavesCallback;

    /**
     *
     * @param pointer
     */
    public Mouse(long pointer) {
        this.pointer = pointer;
        this.buttonChanged = new boolean[GLFW_MOUSE_BUTTON_LAST];
        this.buttons = new int[GLFW_MOUSE_BUTTON_LAST];

        //Sets the callback for the mouse inputs
        glfwSetCursorEnterCallback(this.pointer, this::cursorEnterCallback);
        glfwSetDropCallback();
        glfwSetCursorPosCallback();
        glfwSetScrollCallback();
        glfwSetMouseButtonCallback();
        glfwSetInputMode();
    }

    protected void update() {
        Arrays.fill(this.buttonChanged, false);
    }

    /**
     *
     * @param callback
     */
    public void onCursorEnter(CursorEnterCallback callback) {
        this.cursorEnterCallback = callback;
    }

    /**
     *
     * @param callback
     */
    public void onCursorLeaves(CursorEnterCallback callback) {
        this.cursorLeavesCallback = callback;
    }

    /**
     *
     * @param window
     * @param enter
     */
    private void cursorEnterCallback(long window, boolean enter) {

        //Triggers the user-defined callback
        if(enter) {
            if (cursorEnterCallback != null) {
                cursorEnterCallback.invoke();
            }
        } else {
            if(cursorLeavesCallback != null) {
                cursorLeavesCallback.invoke();
            }
        }
    }

}
