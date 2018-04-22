package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the cursor position callback of a window
 *
 * @author Dorian
 */
@FunctionalInterface
public interface CursorPositionCallback extends WindowCallback {

    /**
     * Cursor position callback for a window
     *
     * <p>
     * Is triggered when the cursor moves on the window
     * </p>
     *
     * @param xPos the X position of the cursor as a rounded <code>double</code>
     * @param yPos the Y position of the cursor as a rounded <code>double</code>
     */
    void invoke(double xPos, double yPos);

}
