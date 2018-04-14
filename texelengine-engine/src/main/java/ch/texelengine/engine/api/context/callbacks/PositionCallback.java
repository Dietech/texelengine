package ch.texelengine.engine.api.context.callbacks;

/**
 * @author Dorian Ros
 */
@FunctionalInterface
public interface PositionCallback extends WindowCallback {

    /**
     *
     * @param xPos
     * @param yPos
     */
    void invoke(int xPos, int yPos);

}
