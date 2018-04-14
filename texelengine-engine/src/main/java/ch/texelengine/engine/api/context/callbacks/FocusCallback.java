package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the focus callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface FocusCallback extends WindowCallback {

    /**
     * Focus callback function for a window
     *
     * <p>
     * Is triggered when the window either gains or loses focus
     * </p>
     */
    void invoke();

}
