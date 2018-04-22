package ch.texelengine.engine.api.context.callbacks;

/**
 * Functional interface that provides a callback function
 * for the file drop callback of a window
 *
 * @author Dorian Ros
 */
@FunctionalInterface
public interface DropCallback extends WindowCallback {

    /**
     * File drop callback for a window
     *
     * <p>
     * Is triggered when files are dropped onto the window
     * </p>
     *
     * @param files the list of file paths dropped
     */
    void invoke(String[] files);

}
