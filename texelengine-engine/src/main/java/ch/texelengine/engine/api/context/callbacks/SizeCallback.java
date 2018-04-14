package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the size callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface SizeCallback extends WindowCallback {

    /**
     * Size callback function for a window
     *
     * <p>
     * Is triggered when the window size changed
     * </p>
     *
     * @param width the new width of the window in screen coordinates
     * @param height the new height of the window in screen coordinates
     */
    void invoke(int width, int height);

}
