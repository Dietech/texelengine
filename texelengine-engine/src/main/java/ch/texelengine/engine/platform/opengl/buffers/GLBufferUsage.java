package ch.texelengine.engine.platform.opengl.buffers;

import ch.texelengine.engine.api.buffers.BufferUsages;

import static org.lwjgl.opengl.GL11.GL_NONE;
import static org.lwjgl.opengl.GL15.*;

/**
 * Interface that provide functions to get OpenGL specific information about
 * different buffer usage
 *
 * @author Dorian Ros
 */
public interface GLBufferUsage {

    /**
     * Get the type id of the {@link BufferUsages} in an OpenGL context
     *
     * @param usage the buffer usage to evaluate
     * @return the OpenGL id of the specified buffer usage
     */
    static int typeOf(BufferUsages usage) {

        if(usage != null) {
            switch(usage) {
                case STATIC:
                    return GL_STATIC_DRAW;
                case DYNAMIC:
                    return GL_DYNAMIC_DRAW;
                default:
                    return GL_NONE;
            }
        } else {
            return GL_NONE;
        }
    }
}
