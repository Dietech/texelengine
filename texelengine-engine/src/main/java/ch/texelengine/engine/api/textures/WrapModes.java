package ch.texelengine.engine.api.textures;

/**
 * Enumerate the different wrap modes in a
 * totally generic way
 *
 * @author Dorian Ros
 */
public enum WrapModes {

    /**
     * Clamp the texture to the edges
     */
    CLAMP,

    /**
     * Repeat the texture if the image is smaller than the target texture
     */
    REPEAT
}
