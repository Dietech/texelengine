package ch.texelengine.engine.api.context.callbacks;

/**
 * @author Dorian Ros
 */
@FunctionalInterface
public interface FocusCallback extends WindowCallback {

    /**
     *
     */
    void invoke();

}
