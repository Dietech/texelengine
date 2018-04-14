package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the close callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface CloseCallback extends WindowCallback {

    /**
     * Close callback function for a window
     *
     * <p>
     * Is triggered when the window should close
     * </p>
     */
    void invoke();

}
