package ch.texelengine.engine.api.buffers;

/**
 * Enumerate the type of usage of a buffer (read-only, etc...)
 *
 * <p>
 * This denominations are API independent
 * </p>
 */
public enum BufferUsages {

    /**
     * Static usage, buffer is readonly
     */
    STATIC,

    /**
     * Dynamic usage, buffer is readwrite
     */
    DYNAMIC
}
