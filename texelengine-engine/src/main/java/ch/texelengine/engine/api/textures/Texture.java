package ch.texelengine.engine.api.textures;

/**
 *
 * @author Dorian
 */
public abstract class Texture {

    /**
     *
     */
    protected int RID;

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


    /**
     *
     * @return
     */
    public int RID() {
        return RID;
    }
}
