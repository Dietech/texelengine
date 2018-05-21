package ch.texelengine.engine.api.shaders;

/**
 * Enumerates the different shader stage types
 *
 * <p>
 * This description is API independent
 * </p>
 *
 * {@author Dorian Ros}
 */
public enum ShaderTypes {

    /**
     * Default value. No type
     */
    NONE,

    /**
     * Vertex shader stage type
     */
    VERTEX,

    /**
     * Fragment shader stage type
     */
    FRAGMENT,

    /**
     * Compute shader stage type
     */
    COMPUTE,

    /**
     * Geometry shader stage type
     */
    GEOMETRY
}
