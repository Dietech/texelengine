package ch.texelengine.engine.api.textures;

import ch.texelengine.engine.platform.opengl.textures.GLTexture2D;

/**
 *
 * @author Dorian
 */
public abstract class Texture2D extends Texture {

    /**
     *
     */
    protected int width;

    /**
     *
     */
    protected int height;

    /**
     *
     * @param width
     * @param height
     * @param filtering
     * @param wrapmode
     * @return
     */
    public static Texture2D create(int width, int height, TextureFormats format, TextureFilters filtering, WrapModes wrapmode) {
        //TODO: switch with context API
        return new GLTexture2D(width, height, format, filtering, wrapmode);
    }

    /**
     *
     * @param file
     * @param loadOptions
     * @param filtering
     * @param wrapmode
     * @return
     */
    public static Texture2D create(String file, TextureLoadOptions loadOptions, TextureFormats format, TextureFilters filtering, WrapModes wrapmode) {
        //TODO: switch with context API
        return new GLTexture2D(file, loadOptions, format, filtering, wrapmode);
    }

    /**
     *
     */
    public void bind() {
        bind(0);
    }

    /**
     *
     * @param unit
     */
    public abstract void bind(int unit);

    /**
     *
     */
    public abstract void unbind();

    /**
     *
     */
    public abstract void destroy();

    /**
     *
     * @return
     */
    public int width() {
        return this.width;
    }

    /**
     *
     * @return
     */
    public int height() {
        return this.height;
    }
}
