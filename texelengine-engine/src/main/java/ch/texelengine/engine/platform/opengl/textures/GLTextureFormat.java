package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.TextureFormats;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Interface that provide functions to get OpenGL specific information about
 * texture formats
 *
 * @author Dorian
 */
public interface GLTextureFormat {

    /**
     * Get the type id of the internal format of a {@link TextureFormats} in an OpenGL context
     *
     * @param format the format option to evaluate
     * @return the OpenGL id of the specified internal format option
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
     * Get the type id of the {@link TextureFormats} in an OpenGL context
     *
     * @param format the format option to evaluate
     * @return the OpenGL id of the specified format option
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
     * Get the type id of the data type of a {@link TextureFormats} in an OpenGL context
     *
     * @param format the format option to evaluate
     * @return the OpenGL id of the specified format data type
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
     * Get the number of channels of a {@link TextureFormats} in an OpenGL context
     *
     * @param format the format option to evaluate
     * @return the number of channels of the specified format option
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
