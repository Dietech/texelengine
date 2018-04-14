package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the position callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface PositionCallback extends WindowCallback {

    /**
     * Position callback function for a window
     *
     * <p>
     * Is triggered when the window changes position on the screen
     * </p>
     *
     * @param xPos the new x position of the window in screen coordinates
     * @param yPos the new y position of the window in screen coordinates
     */
    void invoke(int xPos, int yPos);

}
