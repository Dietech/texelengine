package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the restore callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface RestoreCallback extends WindowCallback {

    /**
     * Restore callback function for a window
     *
     * <p>
     * Is triggered when the window is either restored from a minimized
     * or maximized state
     * </p>
     */
    void invoke();

}
