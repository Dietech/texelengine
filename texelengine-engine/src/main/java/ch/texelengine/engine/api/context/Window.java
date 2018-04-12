package ch.texelengine.engine.api.context;

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

    /**
     * Construct a new {@link Window} and initialize its {@link #pointer} to a
     * created context.
     *
     * @param pointer the pointer to an allready created context
     */
    public Window(long pointer) {
        this.pointer = pointer;
    }

    /**
     *
     */
    public void destroy() {

    }

}
