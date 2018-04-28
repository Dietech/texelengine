package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLTexture2D;

/**
 * Represents a API independent 2D texture that can be loaded
 * in the engine.
 *
 * <p>
 * See the implementation of this class in the API specific classes
 * </p>
 *
 * @see GLTexture2D
 *
 * @author Dorian Ros
 */
public abstract class Texture2D extends Texture {

    /**
     * The width of the texture in pixels
     */
    protected int width;

    /**
     * The height of the texture in pixels
     */
    protected int height;

    /**
     * Create a new empty {@link Texture2D} that corresponds to the current graphics API
     *
     * @param width the width of the texture in pixels
     * @param height the height of the texture in pixels
     * @param format the format of the texture as a {@link TextureFormats}
     * @param filtering the filtering option of the texture as a {@link TextureFilters}
     * @param wrapmode the wrapping mode of the texture as a {@link WrapModes}
     * @return the newly created texture
     */
    public static Texture2D create(int width, int height, TextureFormats format, TextureFilters filtering, WrapModes wrapmode) {
        //TODO: switch with context API
        return new GLTexture2D(width, height, format, filtering, wrapmode);
    }

    /**
     * Create a new {@link Texture2D} that corresponds to the current graphics API and load the image at the specified
     * path
     *
     * @param file the path to the image to load into the texture
     * @param loadOptions the loading options to load the image
     * @param format the format of the texture as a {@link TextureFormats}
     * @param filtering the filtering option of the texture as a {@link TextureFilters}
     * @param wrapmode the wrapping mode of the texture as a {@link WrapModes}
     * @return the newly created texture
     */
    public static Texture2D create(String file, TextureLoadOptions loadOptions, TextureFormats format, TextureFilters filtering, WrapModes wrapmode) {
        //TODO: switch with context API
        return new GLTexture2D(file, loadOptions, format, filtering, wrapmode);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This is the same as calling {@link #bind(int)} with a <tt>0</tt> parameter
     * </p>
     */
    public void bind() {
        bind(0);
    }

    /**
     * {@inheritDoc}
     */
    public abstract void bind(int unit);

    /**
     * {@inheritDoc}
     */
    public abstract void unbind();

    /**
     * Destroy <code>this</code> texture object and release its data
     */
    public abstract void destroy();

    /**
     * Get the width of <code>this</code> texture
     *
     * @return the width of the texture in pixels
     */
    public int width() {
        return this.width;
    }

    /**
     * Get the height of <code>this</code> texture
     *
     * @return the height of the texture in pixels
     */
    public int height() {
        return this.height;
    }
}
