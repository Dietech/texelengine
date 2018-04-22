package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLTextureFilter;

/**
 *
 * @author Dorian
 */
public interface TextureFilter {

    /**
     *
     * @param filter
     * @return
     */
    static int filterOf(TextureFilters filter) {
        //TODO: switch with context API
        return GLTextureFilter.filterOf(filter);
    }

}
