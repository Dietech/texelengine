package ch.texelengine.engine.api.context.callbacks;

/**
 * @author Dorian Ros
 */
@FunctionalInterface
public interface CloseCallback extends WindowCallback {

    /**
     *
     */
    void invoke();

}
