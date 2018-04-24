package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.TextureFilters;

import static org.lwjgl.opengl.GL11.*;

/**
 * Interface that provide functions to get OpenGL specific information about
 * texture filters
 *
 * @author Dorian
 */
public interface GLTextureFilter {

    /**
     * Get the type id of the {@link TextureFilters} in an OpenGL context
     *
     * @param filter the filter option to evaluate
     * @return the OpenGL id of the specified filter option
     */
    static int filterOf(TextureFilters filter) {
        if(filter != null) {
            switch(filter) {
                case LINEAR:
                    return GL_LINEAR;
                case NEAREST:
                    return GL_NEAREST;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * Get the type id of the mipmap {@link TextureFilters} in an OpenGL context
     *
     * @param filter the filter option to evaluate
     * @return the OpenGL id of the specified mipmap filter option
     */
    static int mipmapFilterOf(TextureFilters filter) {
       if(filter != null) {
           switch(filter) {
               case NEAREST:
                   return GL_NEAREST_MIPMAP_LINEAR;
               case LINEAR:
                   return GL_LINEAR_MIPMAP_LINEAR;
               default:
                   return 0;
           }
       } else {
           return 0;
       }
    }
}
