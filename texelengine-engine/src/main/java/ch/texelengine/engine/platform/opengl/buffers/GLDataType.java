package ch.texelengine.engine.platform.opengl.buffers;

import static org.lwjgl.opengl.GL11.*;

import ch.texelengine.engine.api.buffers.DataTypes;

/**
 * Interface that provide functions to get OpenGL specific information about
 * different data types
 *
 * @author Dorian Ros
 */
public interface GLDataType {

    /**
     * Get the size in byte of the {@link DataTypes} in an OpenGL context
     *
     * @param type the type to evaluate
     * @return the byte size of the type
     */
    static int sizeOf(DataTypes type) {

        if(type != null) {
            switch(type) {
                case FLOAT:
                    return 4;
                case UINT:
                    return 4;
                case UBYTE:
                    return 1;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * Get the type id of the {@link DataTypes} in an OpenGL context
     *
     * @param type the type to evaluate
     * @return the OpenGL id of the specified type
     */
    static int typeOf(DataTypes type) {

        if(type != null) {
            switch(type) {
                case FLOAT:
                    return GL_FLOAT;
                case UINT:
                    return GL_UNSIGNED_INT;
                case UBYTE:
                    return GL_UNSIGNED_BYTE;
                default:
                    return GL_NONE;
            }
        } else {
            return GL_NONE;
        }
    }
}
