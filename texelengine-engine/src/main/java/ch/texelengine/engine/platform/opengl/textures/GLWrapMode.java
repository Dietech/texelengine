package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.WrapModes;

import static org.lwjgl.opengl.GL11.*;

/**
 * Interface that provide functions to get OpenGL specific information about
 * texture wrap modes
 *
 * @author Dorian
 */
public interface GLWrapMode {

    /**
     * Get the type id of a {@link WrapModes} in an OpenGL context
     *
     * @param mode the wrap mode option to evaluate
     * @return the OpenGL id of the specified wrap mode option
     */
    static int modeOf(WrapModes mode) {
        if(mode != null) {
            switch (mode) {
                case REPEAT:
                    return GL_REPEAT;
                case CLAMP:
                    return GL_CLAMP;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }
}
