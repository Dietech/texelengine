package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.TextureFormats;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * @author Dorian Ros
 */
public interface GLTextureFormat {

    /**
     *
     * @param format
     * @return
     */
    static int internalFormatOf(TextureFormats format) {
        if(format != null) {
            switch(format) {
                case RGB8:
                    return GL_RGB8;
                case RGB16F:
                    return GL_RGB16F;
                case RGB32F:
                    return GL_RGB32F;
                case RGBA8:
                    return GL_RGBA8;
                case RGBA16F:
                    return GL_RGBA16F;
                case RGBA32F:
                    return GL_RGBA32F;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     *
     * @param format
     * @return
     */
    static int formatOf(TextureFormats format) {
        if(format != null) {
            switch(format) {
                case RGB8:
                case RGB16F:
                case RGB32F:
                    return GL_RGB;
                case RGBA8:
                case RGBA16F:
                case RGBA32F:
                    return GL_RGBA;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     *
     * @param format
     * @return
     */
    static int typeOf(TextureFormats format) {
        if(format != null) {
            switch(format) {
                case RGB8:
                case RGBA8:
                    return GL_UNSIGNED_BYTE;
                case RGB16F:
                case RGB32F:
                case RGBA16F:
                case RGBA32F:
                    return GL_FLOAT;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     *
     * @param format
     * @return
     */
    static int strideOf(TextureFormats format) {
        if(format != null) {
            switch(format) {
                case RGB8:
                case RGB16F:
                case RGB32F:
                    return 3;
                case RGBA8:
                case RGBA16F:
                case RGBA32F:
                    return 4;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

}
