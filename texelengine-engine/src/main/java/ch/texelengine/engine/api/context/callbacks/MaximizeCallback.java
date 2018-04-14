package ch.texelengine.engine.api.context.callbacks;

/**
 * @author Dorian Ros
 */
@FunctionalInterface
public interface MaximizeCallback extends WindowCallback {

    /**
     *
     */
    void invoke();

}
