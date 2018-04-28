package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLTextureFormat;

/**
 * Interface that provides API independent functions to get information about
 * texture formats
 *
 * <p>
 * See the API specific implementation for more details
 * </p>
 *
 * @see GLTextureFormat
 *
 * @author Dorian Ros
 */
public interface TextureFormat {

    /**
     * Get the format parameter for a generic format enumeration entry
     *
     * @param format the format as a {@link TextureFormats}
     * @return the API specific identifier for the format option
     */
    static int formatOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.formatOf(format);
    }

    /**
     * Get the internal format parameter for a generic format enumeration entry
     *
     * @param format the format as a {@link TextureFormats}
     * @return the API specific identifier for the internal format option
     */
    static int internalFormatOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.internalFormatOf(format);
    }

    /**
     * Get the type of data of a generic format enumeration entry
     *
     * @param format the format as a {@link TextureFormats}
     * @return the API specific type of data for the format
     */
    static int typeOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.typeOf(format);
    }

    /**
     * Get the stride of a generic format enumeration entry
     *
     * @param format the format as a {@link TextureFormats}
     * @return the number of channels of the format
     */
    static int strideOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.strideOf(format);
    }

}
