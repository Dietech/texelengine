package ch.texelengine.engine.api.textures;

/**
 * Represents a generic, API independent, texture that can be loaded
 * in the engine.
 *
 * <p>
 * See the implementation of this class in the API specific classes
 * </p>
 *
 * @author Dorian Ros
 */
public abstract class Texture {

    /**
     * Unique engine identifier for the object
     */
    protected int RID;

    /**
     * Bind <code>this</code> texture so that it can be modified by texture calls
     */
    public abstract void bind();

    /**
     * Bind <code>this</code> texture to a unit so that it can be modified by texture calls
     *
     * <p>
     * Most graphics cards have a maximum of 32 texture units
     * </p>
     *
     * @param unit the unit to which to bind the texture
     */
    public abstract void bind(int unit);

    /**
     * Unbind <code>this</code> if previously bound
     */
    public abstract void unbind();


    /**
     * Get the {@link #RID} of <code>this</code> texture
     *
     * @return the RID of the texture
     */
    public int RID() {
        return RID;
    }
}
