package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the minimize callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface MinimizeCallback extends WindowCallback {

    /**
     * Minimize callback function for a window
     *
     * <p>
     * Is triggered when the window is minimized in the system tray
     * </p>
     */
    void invoke();

}
