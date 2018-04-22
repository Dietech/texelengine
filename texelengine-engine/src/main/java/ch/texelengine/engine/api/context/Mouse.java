package ch.texelengine.engine.api.context;

import ch.texelengine.engine.api.context.callbacks.CursorEnterCallback;
import ch.texelengine.engine.api.context.callbacks.CursorPositionCallback;
import ch.texelengine.engine.api.context.callbacks.DropCallback;
import ch.texelengine.engine.api.context.callbacks.ScrollCallback;
import org.lwjgl.glfw.GLFWDropCallback;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Handles the mouse inputs for a GLFW context
 *
 * @author Dorian Ros
 */
public class Mouse {

    /**
     * Pointer to a GLFW context to read the input from
     */
    private long pointer;

    /**
     * Holds a state change for any mouse button
     *
     * <p>
     * The flag is set to <code>true</code> for exactly one frame
     * </p>
     */
    private boolean[] buttonChanged;

    /**
     * Holds the last state of all the mouse buttons in the GLFW format
     *
     * <p>
     * This array is updated at every call to {@link Context#pollEvents()}
     * </p>
     */
    private int[] lastButtons;

    /**
     * X position of the cursor on the window
     *
     * <p>
     * If the input is set to grab with {@link #grabCursor()} then the coordinates can be greater
     * than the size of the window
     * </p>
     */
    private double cursorXPos;

    /**
     * Y position of the cursor on the window
     *
     * <p>
     * If the input is set to grab with {@link #grabCursor()} then the coordinates can be greater
     * than the size of the window
     * </p>
     */
    private double cursorYPos;

    /**
     * The user-defined {@link CursorEnterCallback}
     *
     * <p>
     * Is triggered when the cursor enters the window
     * </p>
     */
    private CursorEnterCallback cursorEnterCallback;

    /**
     * The user-defined {@link CursorEnterCallback}
     *
     * <p>
     * Is triggered when the cursor leaves the window
     * </p>
     */
    private CursorEnterCallback cursorLeavesCallback;

    /**
     * The user-defined {@link CursorPositionCallback}
     *
     * <p>
     * Is triggered when the cursor changes position on the window
     * </p>
     */
    private CursorPositionCallback cursorPositionCallback;

    /**
     * The user-defined {@link ScrollCallback}
     *
     * <p>
     * Is triggered when the mouse is scrolling
     * </p>
     */
    private ScrollCallback scrollCallback;

    /**
     * Construct a new {@link Mouse} object
     *
     * @param pointer the pointer to the GLFW context to read the inputs from
     */
    public Mouse(long pointer) {
        this.pointer = pointer;
        this.buttonChanged = new boolean[GLFW_MOUSE_BUTTON_LAST];
        this.lastButtons = new int[GLFW_MOUSE_BUTTON_LAST];

        //Sets the callback for the mouse inputs
        glfwSetCursorEnterCallback(this.pointer, this::cursorEnterCallback);
        glfwSetCursorPosCallback(this.pointer, this::cursorPositionCallback);
        glfwSetScrollCallback(this.pointer, this::scrollCallback);
        glfwSetMouseButtonCallback(this.pointer, this::buttonCallback);
    }

    /**
     * Resets the state of the button changes array
     *
     * <p>
     * Needs to be called once per frame before the {@link org.lwjgl.glfw.GLFW#glfwPollEvents} call
     * </p>
     */
    protected void update() {
        Arrays.fill(this.buttonChanged, false);
    }

    /**
     * Button callback function to update the button state arrays
     *
     * @param window the pointer to a GLFW context
     * @param button the button code of the event
     * @param action the action of the event in the GLFW format
     * @param mods a bitfield that stores the state of the modifier keys
     */
    private void buttonCallback(long window, int button, int action, int mods) {
        if(button != GLFW_KEY_UNKNOWN) {
            buttonChanged[button] = lastButtons[button] != action;
            lastButtons[button] = action;
        }
    }

    /**
     * Get whether a button was just pressed
     *
     * @param buttoncode the mouse button code in the GLFW format
     * @return the state of the button
     */
    public boolean isButtonPressed(int buttoncode) {
        return buttonChanged[buttoncode] && lastButtons[buttoncode] == GLFW_PRESS;
    }

    /**
     * Get whether a button is being pressed
     *
     * @param buttoncode the mouse button code in the GLFW format
     * @return the statue of the button
     */
    public boolean isButtonDown(int buttoncode) {
        return lastButtons[buttoncode] != GLFW_RELEASE;
    }

    /**
     * Get whether a button is being held down
     *
     * @param buttoncode the mouse button code in the GLFW format
     * @return the status of the button
     */
    public boolean isButtonHeld(int buttoncode) {
        return lastButtons[buttoncode] == GLFW_REPEAT;
    }

    /**
     * Get whether a button was just released
     *
     * @param buttoncode the button code in the GLFW format
     * @return the status of the button
     */
    public boolean isKeyReleased(int buttoncode) {
        return buttonChanged[buttoncode] && lastButtons[buttoncode] == GLFW_RELEASE;
    }

    /**
     * Hide the cursor in on the current context window
     */
    public void hideCursor() {
        glfwSetInputMode(this.pointer, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
    }

    /**
     * Show the cursor if hidden on the current context window
     */
    public void showCursor() {
        glfwSetInputMode(this.pointer, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    /**
     * Hide and grab the cursor on the current context window and enable free movement
     *
     * <p>
     * In this mode the position of the cursor can exceed the size of the window.
     * This is useful to create 3D camera movement.
     * </p>
     */
    public void grabCursor() {
        glfwSetInputMode(this.pointer, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    /**
     * Get the X position of the cursor
     *
     * @return the X position as a rounded <code>double</code>
     */
    public double mouseX() {
        return cursorXPos;
    }

    /**
     * Get the Y position of the cursor
     *
     * @return the Y position as a rounded <code>double</code>
     */
    public double mouseY() {
        return cursorYPos;
    }

    /**
     * Set the cursor enter callback for <code>this</code> mouse input
     *
     * @param callback the {@link CursorEnterCallback} for the mouse input
     */
    public void onCursorEnter(CursorEnterCallback callback) {
        this.cursorEnterCallback = callback;
    }

    /**
     * Set the cursor leave callback for <code>this</code> mouse input
     *
     * @param callback the {@link CursorEnterCallback} for the mouse input
     */
    public void onCursorLeaves(CursorEnterCallback callback) {
        this.cursorLeavesCallback = callback;
    }

    /**
     * Set the cursor move callback for <code>this</code> mouse input
     *
     * @param callback the {@link CursorPositionCallback callback} for the mouse input
     */
    public void onCursorMove(CursorPositionCallback callback) {
        this.cursorPositionCallback = callback;
    }

    /**
     * Set the scroll callback for <code>this</code> mouse input
     *
     * @param callback the {@link ScrollCallback} the the mouse input
     */
    public void onScroll(ScrollCallback callback) {
        this.scrollCallback = callback;
    }

    /**
     * Cursor enter callback function
     *
     * @param window the pointer to the GLFW context
     * @param enter whether the cursor enters or leaves the window
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

    /**
     * Cursor position callback function
     *
     * @param window the pointer for the GLFW context
     * @param xPos the X position of the cursor as a rounded <code>double</code>
     * @param yPos the Y position of the cursor as a rounded <code>double</code>
     */
    private void cursorPositionCallback(long window, double xPos, double yPos) {
        cursorXPos = xPos;
        cursorYPos = yPos;

        //Triggers user-defined callback
        if(cursorPositionCallback != null) {
            cursorPositionCallback.invoke(xPos, yPos);
        }
    }

    /**
     * Scroll callback function
     *
     * @param window the pointer to the GLFW context
     * @param xOffset the X scroll offset
     * @param yOffset the Y scroll offset
     */
    private void scrollCallback(long window, double xOffset, double yOffset) {

        //Triggers user-defined callback
        if(scrollCallback != null) {
            scrollCallback.invoke(xOffset, yOffset);
        }
    }
}

