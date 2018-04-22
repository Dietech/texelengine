package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.TextureFilters;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Dorian
 */
public interface GLTextureFilter {

    /**
     *
     * @param filter
     * @return
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
     *
     * @param filter
     * @return
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
