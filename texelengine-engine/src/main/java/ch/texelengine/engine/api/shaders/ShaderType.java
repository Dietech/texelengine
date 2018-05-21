package ch.texelengine.engine.api.shaders;

import ch.texelengine.engine.platform.opengl.shaders.GLShaderType;

/**
 * Interface that provide functions to get API independent information about
 * different shader types
 *
 * <p>
 * See the implementation of this functions in the API specific classes
 * </p>
 *
 * @see GLShaderType
 *
 * {@author Dorian Ros}
 */
public interface ShaderType {

    /**
     * Get the type identifier for the corresponding shader stage type in an
     * API independent fashion
     *
     * @param type shader stage type to get the identifier from
     * @return the identifier of the shader stage type
     */
    static int typeOf(ShaderTypes type) {
        //TODO: switch with context API
        return GLShaderType.typeOf(type);
    }
}
