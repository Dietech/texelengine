package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the refresh callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface RefreshCallback extends WindowCallback {

    /**
     * Refresh callback function for a window
     *
     * <p>
     * Is triggered when the window is refreshed
     * </p>
     */
    void invoke();

}
