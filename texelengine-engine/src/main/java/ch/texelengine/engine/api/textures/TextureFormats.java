package ch.texelengine.engine.api.textures;

/**
 * Enumerate the different types of texture format options in a
 * totally generic way
 *
 * @author Dorian Ros
 */
public enum TextureFormats {

    /**
     * Red, Green, Blue, Alpha 8 bits per channel
     */
    RGBA8,

    /**
     * Red, Green, Blue, Alpha 16 bits per channel, floating point
     */
    RGBA16F,

    /**
     * Red, Green, Blue, Alpha 32 bits per channel, floating point
     */
    RGBA32F,

    /**
     * Red, Green, Blue, 8 bits per channel
     */
    RGB8,

    /**
     * Red, Green, Blue, 16 bits per channel, floating point
     */
    RGB16F,

    /**
     * Red, Green, Blue, 32 bits per channel, floating point
     */
    RGB32F
}
