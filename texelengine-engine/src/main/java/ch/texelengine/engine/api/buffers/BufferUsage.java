package ch.texelengine.engine.api.buffers;

import ch.texelengine.engine.platform.opengl.buffers.GLBufferUsage;

/**
 * Interface that provide functions to get API independent information about
 * different buffer usages
 *
 * <p>
 * See the implementation of this functions in the API specific classes
 * </p>
 *
 * @see ch.texelengine.engine.platform.opengl.buffers.GLBufferUsage
 *
 * @author Dorian Ros
 */
public interface BufferUsage {

    /**
     * Get the type id of the {@link BufferUsages}
     *
     * <p>
     * This function is API independent
     * </p>
     *
     * @param usage the usage to evaluate
     * @return the id of the specified buffer usage
     */
    static int usageId(BufferUsages usage) {
        //TODO: switch with context API
        return GLBufferUsage.typeOf(usage);
    }
}
