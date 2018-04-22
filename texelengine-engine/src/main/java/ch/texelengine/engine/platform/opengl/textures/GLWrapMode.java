package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.WrapModes;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Dorian
 */
public interface GLWrapMode {

    /**
     *
     * @param mode
     * @return
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
