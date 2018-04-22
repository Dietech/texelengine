package ch.texelengine.engine.api.textures;

/**
 *
 * @author Dorian
 */
public abstract class Texture {

    /**
     *
     */
    protected TextureFilter filtering;

    /**
     *
     */
    protected WrapMode wrapmode;

    /**
     *
     */
    public abstract void bind();

    /**
     *
     * @param unit
     */
    public abstract void bind(int unit);

    /**
     *
     */
    public abstract void unbind();
}
