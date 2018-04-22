package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLWrapMode;

/**
 * @author Dorian
 */
public interface WrapMode {

    /**
     *
     * @param mode
     * @return
     */
    static int modeOf(WrapModes mode) {
        //TODO: switch with context API
        return GLWrapMode.modeOf(mode);
    }
}
