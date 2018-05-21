package ch.texelengine.engine.api.shaders;

import ch.texelengine.engine.platform.opengl.shaders.GLShaderType;

/**
 *
 */
public interface ShaderType {

    /**
     *
     * @param type
     * @return
     */
    static int typeOf(ShaderTypes type) {
        //TODO: switch with context API
        return GLShaderType.typeOf(type);
    }
}
