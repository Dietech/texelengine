package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLWrapMode;

/**
 * Interface that provides API independent functions to get information about
 * texture wrapping modes
 *
 * <p>
 * See the API specific implementation for more details
 * </p>
 *
 * @see GLWrapMode
 *
 * @author Dorian Ros
 */
public interface WrapMode {

    /**
     * Get the wrap mode parameter for a generic wrap mode enumeration entry
     *
     * @param mode the format as a {@link TextureFormats}
     * @return the API specific identifier for the wrap mode option
     */
    static int modeOf(WrapModes mode) {
        //TODO: switch with context API
        return GLWrapMode.modeOf(mode);
    }
}
