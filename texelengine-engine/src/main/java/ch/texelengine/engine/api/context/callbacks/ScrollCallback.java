package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the scroll callback of a window
 *
 * @author Dorian
 */
@FunctionalInterface
public interface ScrollCallback extends WindowCallback {

    /**
     * Scroll callback for a window
     *
     * <p>
     * Is triggered when the mouse is scrolled on the window
     * </p>
     *
     * @param xOffset the scroll offset on the X axis
     * @param yOffset the scroll offset on the Y axis
     */
    void invoke(double xOffset, double yOffset);

}
