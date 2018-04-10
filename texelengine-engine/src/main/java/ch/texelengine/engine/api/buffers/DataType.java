package ch.texelengine.engine.api.buffers;

import ch.texelengine.engine.platform.opengl.buffers.GLDataType;

/**
 * Interface that provide functions to get API independent information about
 * different data types
 *
 * <p>
 * See the implementation of this functions in the API specific classes
 * </p>
 *
 * @see ch.texelengine.engine.platform.opengl.buffers.GLDataType
 *
 * @author Dorian Ros
 */
public interface DataType {

    /**
     * Get the size in byte of the {@link DataTypes}
     *
     * <p>
     * This function is API independent
     * </p>
     *
     * @param type the type to evaluate
     * @return the byte size of the type
     */
    static int sizeOf(DataTypes type) {
        //TODO: switch with context API
        return GLDataType.sizeOf(type);
    }

    /**
     * Get the type id of the {@link DataTypes}
     *
     * <p>
     * This function is API independent
     * </p>
     *
     * @param type the type to evaluate
     * @return the id of the specified type
     */
    static int typeId(DataTypes type) {
        //TODO: switch with context API
        return GLDataType.typeOf(type);
    }
}
