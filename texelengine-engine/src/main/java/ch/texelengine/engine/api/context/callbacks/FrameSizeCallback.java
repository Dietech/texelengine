package ch.texelengine.engine.api.context.callbacks;

/**
 * @author Dorian Ros
 */
@FunctionalInterface
public interface FrameSizeCallback extends WindowCallback {

    /**
     *
     * @param width
     * @param height
     */
    void invoke(int width, int height);

}
