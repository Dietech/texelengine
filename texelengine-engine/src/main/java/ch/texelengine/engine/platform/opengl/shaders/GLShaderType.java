package ch.texelengine.engine.platform.opengl.shaders;

import ch.texelengine.engine.api.shaders.ShaderTypes;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL43.*;

/**
 *
 */
public interface GLShaderType {

    /**
     *
     * @param type
     * @return
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
