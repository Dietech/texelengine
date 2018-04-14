package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the framebuffer size callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface FrameSizeCallback extends WindowCallback {

    /**
     * Framebuffer size callback function for a window
     *
     * <p>
     * Is triggered when the window's framebuffer size changed
     * </p>
     *
     * @param width the new width of the framebuffer in pixels
     * @param height the new height of the framebuffer in pixels
     */
    void invoke(int width, int height);

}
