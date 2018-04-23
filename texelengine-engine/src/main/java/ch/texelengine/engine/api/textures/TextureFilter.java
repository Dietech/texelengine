package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLTextureFilter;

/**
 * Interface that provides API independent functions to get information about
 * texture filters
 *
 * <p>
 * See the API specific implementation for more details
 * </p>
 *
 * @see GLTextureFilter
 *
 * @author Dorian Ros
 */
public interface TextureFilter {

    /**
     * Get the filter parameter for a generic filter enumeration entry
     *
     * @param filter the filter as a {@link TextureFilters}
     * @return the API specific identifier for the filter option
     */
    static int filterOf(TextureFilters filter) {
        //TODO: switch with context API
        return GLTextureFilter.filterOf(filter);
    }

}
