package ch.texelengine.engine.api.context.callbacks;

/**
 * @author Dorian Ros
 */
@FunctionalInterface
public interface RefreshCallback extends WindowCallback {

    /**
     *
     */
    void invoke();

}
