package ch.texelengine.engine.api.context.callbacks;

/**
 * @author Dorian Ros
 */
@FunctionalInterface
public interface MinimizeCallback extends WindowCallback {

    /**
     *
     */
    void invoke();

}
