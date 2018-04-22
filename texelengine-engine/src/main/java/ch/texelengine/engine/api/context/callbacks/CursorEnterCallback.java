package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the cursor enter callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface CursorEnterCallback extends WindowCallback {

    /**
     * Cursor enter callback for a window
     *
     * <p>
     * Is triggered when the cursor enters or leaves the window
     * </p>
     */
    void invoke();

}
