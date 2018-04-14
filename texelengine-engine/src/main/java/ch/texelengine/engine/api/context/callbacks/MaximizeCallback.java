package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the maximize callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface MaximizeCallback extends WindowCallback {

    /**
     * Maximize callback function for a window
     *
     * <p>
     * Is triggered when the window is maximized
     * </p>
     */
    void invoke();

}
