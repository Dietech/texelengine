package ch.texelengine.engine.platform.opengl.shaders;

import ch.texelengine.engine.api.shaders.ShaderTypes;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL43.*;

/**
 * Interface that provide functions to get OpenGL specific information about
 * different shader stage types
 *
 * @author Dorian Ros
 */
public interface GLShaderType {

    /**
     * Get the OpenGL identifier for a shader stage type
     *
     * @param type the shader stage type to get the identifier from
     * @return the identifier of the given shader stage type or 0 if an error occurred
     */
    static int typeOf(ShaderTypes type) {
        if(type != null) {
            switch(type) {
                case NONE:
                    return 0;
                case VERTEX:
                    return GL_VERTEX_SHADER;
                case FRAGMENT:
                    return GL_FRAGMENT_SHADER;
                case COMPUTE:
                    return GL_COMPUTE_SHADER; //TODO: Not supported if context is not 4.3
                case GEOMETRY:
                    return GL_GEOMETRY_SHADER;
                default:
                    return 0;
            }
        }
        return 0;
    }
}
