package ch.texelengine.engine.api.buffers;

/**
 * Represent a data element that can be combined into buffer layouts
 *
 * @author Dorian Ros
 */
public final class VertexBufferElement {

    /**
     * Type of data of the element
     */
    private final DataTypes type;

    /**
     * Number of type element
     */
    private final int count;

    /**
     * Flag that indicates if the data is normalized
     */
    private final boolean normalized;

    /**
     * Construct a new {@link VertexBufferElement}
     *
     * @param type       the type of the data of the element
     * @param count      the number of type element
     * @param normalized flag that indicate if the data is normalized
     */
    public VertexBufferElement(DataTypes type, int count, boolean normalized) {
        this.type = type;
        this.count = count;
        this.normalized = normalized;
    }

    /**
     * Get the type of <code>this</code> vertex buffer element
     *
     * @return the type of the element
     */
    public DataTypes type() {
        return type;
    }

    /**
     * Get the number of type element in <code>this</code> element
     *
     * @return the number of type element
     */
    public int count() {
        return count;
    }

    /**
     * Get the normalize flag
     *
     * @return the normalize flag
     */
    public boolean normalised() {
        return normalized;
    }
}
