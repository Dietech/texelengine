package ch.texelengine.engine.api.buffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Interface that provide functions to create and manipulate Java NIO buffers
 *
 * @see java.nio.Buffer
 *
 * @author Dorian Ros
 */
public interface BufferUtils {

    /**
     * Create a {@link FloatBuffer} and store the given data array into it
     *
     * @param data the data to store
     * @return the created float buffer
     */
    static FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     *
     * @param capacity
     * @return
     */
    static FloatBuffer createFloatBuffer(int capacity) {
        return ByteBuffer.allocateDirect(capacity << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    /**
     * Create a {@link IntBuffer} and store the given data array into it
     *
     * @param data the data to store
     * @return the created int buffer
     */
    static IntBuffer createIntBuffer(int[] data) {
        IntBuffer buffer = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     *
     * @param capacity
     * @return
     */
    static IntBuffer createIntBuffer(int capacity) {
        return ByteBuffer.allocateDirect(capacity << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
    }
}
