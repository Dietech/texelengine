package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLTextureFormat;

/**
 * @author Dorian Ros
 */
public interface TextureFormat {

    /**
     *
     * @param format
     * @return
     */
    static int formatOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.formatOf(format);
    }

    /**
     *
     * @param format
     * @return
     */
    static int internalFormatOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.internalFormatOf(format);
    }

    /**
     *
     * @param format
     * @return
     */
    static int typeOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.typeOf(format);
    }

    /**
     *
     * @param format
     * @return
     */
    static int strideOf(TextureFormats format) {
        //TODO: switch with context API
        return GLTextureFormat.strideOf(format);
    }

}
