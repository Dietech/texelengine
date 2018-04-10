package ch.texelengine.engine.api.buffers;

import ch.texelengine.math.linearalgebra.Vector2f;
import ch.texelengine.math.linearalgebra.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a vertex buffer layout object that describes the data layout of a {@link VertexBuffer}
 *
 * <p>
 * This describes how every element of the buffer is laid out in memory
 * </p>
 *
 * @author Dorian Ros
 */
public final class VertexBufferLayout {

    /**
     * Size in byte the layout element
     */
    private int stride;

    /**
     * List of data elements (integers, vectors) that compose the buffer layout
     */
    private final List<VertexBufferElement> layout;

    /**
     * Construct a new {@link VertexBufferLayout}
     *
     * @return the created vertex buffer layout
     */
    public VertexBufferLayout() {
        layout = new ArrayList<>();
    }

    /**
     * Add floats values to the layout
     *
     * @see #pushElement(DataTypes, int, boolean)
     *
     * @param count the number of float to add
     * @return this
     */
    public VertexBufferLayout pushFloats(int count) {
        return pushElement(DataTypes.FLOAT, count, false);
    }

    /**
     * Add unsigned integer values to the layout
     *
     * @see #pushElement(DataTypes, int, boolean)
     *
     * @param count the number of <code>uint</code> to add
     * @return this
     */
    public VertexBufferLayout pushUnsignedIntegers(int count) {
        return pushElement(DataTypes.UINT, count, false);
    }

    /**
     * Add byte values to the layout
     *
     * @see #pushElement(DataTypes, int, boolean)
     *
     * @param count the number of unsigned byte to add
     * @return this
     */
    public VertexBufferLayout pushBytes(int count) {
        return pushElement(DataTypes.UBYTE, count, true);
    }

    /**
     * Add {@link Vector2f} to the layout
     *
     * <p>
     * This is the same as adding 2 floats
     * </p>
     *
     * @see #pushFloats(int)
     * @see #pushElement(DataTypes, int, boolean)
     *
     * @param count the number of vector to add
     * @return this
     */
    public VertexBufferLayout pushVector2f(int count) {
        return pushElement(DataTypes.FLOAT, count * 2, false);
    }

    /**
     * Add {@link Vector3f} to the layout
     *
     * <p>
     * This is the same as adding 3 floats
     * </p>
     *
     * @see #pushFloats(int)
     * @see #pushElement(DataTypes, int, boolean)
     *
     * @param count the number of vector to add
     * @return this
     */
    public VertexBufferLayout pushVector3f(int count) {
        return pushElement(DataTypes.FLOAT, count * 3, false);
    }

    /**
     * Add internally a positive amount of {@link DataTypes} to <code>this</code> layout
     *
     * @param type the type of data to add
     * @param count the number of elements to add
     * @param normalized the flag that defines whether the data is normalized
     *
     * @throws IllegalArgumentException the count is less or equal than zero
     *
     * @return this
     */
    private VertexBufferLayout pushElement(DataTypes type, int count, boolean normalized) throws IllegalArgumentException {

        if(count <= 0) {
            throw new IllegalArgumentException("Count is less or equal than zero: " + count);
        }
        layout.add(new VertexBufferElement(type, count, normalized));
        stride += count * DataType.sizeOf(type);
        return this;
    }

    /**
     * Get the {@link #stride} of <code>this</code> buffer layout
     *
     * @return the stride
     */
    public int stride() {
        return this.stride;
    }

    /**
     * Get the list of {@link VertexBufferElement} that form the layout
     *
     * @return the {@link #layout} list
     */
    public List<VertexBufferElement> layout() {
        return this.layout;
    }
}
