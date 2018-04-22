package ch.texelengine.engine.api.context;

import org.lwjgl.glfw.GLFW;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Handles the keyboard inputs for a GLFW context
 *
 * @author Dorian Ros
 */
public class Keyboard {

    /**
     * Pointer to the GLFW context to read the inputs from
     */
    private long pointer;

    /**
     * Holds a state change for all the keyboard keys
     *
     * <p>
     * The flag is <code>true</code> for exactly one frame
     * </p>
     */
    private boolean[] keyChanged;

    /**
     * Holds the last state of all the keyboard keys in the GLFW format
     *
     * <p>
     * The values are updated at each call of {@link Context#pollEvents()}
     * </p>
     */
    private int[] lastKeys;

    /**
     * Construct a new {@Keyboard} object
     *
     * @param pointer the pointer to the targeted GLFW context
     */
    public Keyboard(long pointer) {
        this.pointer = pointer;
        keyChanged = new boolean[GLFW_KEY_LAST];
        lastKeys = new int[GLFW_KEY_LAST];

        //Set the key callback
        glfwSetKeyCallback(this.pointer, this::keyCallback);
    }

    /**
     * Resets the state of the key changes array
     *
     * <p>
     * Needs to be called once per frame before the {@link org.lwjgl.glfw.GLFW#glfwPollEvents} call
     * </p>
     */
    protected void update() {
        Arrays.fill(keyChanged, false);
    }

    /**
     * Key callback to update the local key states
     *
     * @param window the pointer to the GLFW context
     * @param key the key that had a state change
     * @param scancode the scancode of the key
     * @param action the new state of the key in the GLFW format
     * @param mods a bitfield that stores the state of the modifier keys
     */
    private void keyCallback(long window, int key, int scancode, int action, int mods) {
        if(key != GLFW_KEY_UNKNOWN) {
            keyChanged[key] = lastKeys[key] != action;
            lastKeys[key] = action;
        }
    }

    /**
     * Get whether a key was just pressed
     *
     * @param keycode the key code in the GLFW format
     * @return the state of the key
     */
    public boolean isKeyPressed(int keycode) {
        return keyChanged[keycode] && lastKeys[keycode] == GLFW_PRESS;
    }

    /**
     * Get whether a key is pressed
     *
     * @param keycode the key code in the GLFW format
     * @return the state of the key
     */
    public boolean isKeyDown(int keycode) {
        return lastKeys[keycode] != GLFW_RELEASE;
    }

    /**
     * Get whether a key is being held down
     *
     * @param keycode the key code in the GLFW format
     * @return the state of the key
     */
    public boolean isKeyHeld(int keycode) {
        return lastKeys[keycode] == GLFW_REPEAT;
    }

    /**
     * Get whether a key was just released
     *
     * @param keycode the key code in the GLFW format
     * @return the state of the key
     */
    public boolean isKeyReleased(int keycode) {
        return keyChanged[keycode] && lastKeys[keycode] == GLFW_RELEASE;
    }
}
