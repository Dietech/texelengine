package ch.texelengine.engine.platform.opengl.buffers;

import ch.texelengine.engine.api.buffers.DataTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.lwjgl.opengl.GL11.*;

class GLDataTypeTest {

    @Test
    public void sizeOfGivesCorrectSize() {
        assertEquals(GLDataType.sizeOf(DataTypes.FLOAT), 4);
        assertEquals(GLDataType.sizeOf(DataTypes.UBYTE), 1);
        assertEquals(GLDataType.sizeOf(DataTypes.UINT), 4);
    }

    @Test
    public void sizeOfGivesZeroWithNullType() {
        assertEquals(GLDataType.sizeOf(null), 0);
    }

    @Test
    public void typeOfGivesCorrectSize() {
        assertEquals(GLDataType.typeOf(DataTypes.FLOAT), GL_FLOAT);
        assertEquals(GLDataType.typeOf(DataTypes.UBYTE), GL_UNSIGNED_BYTE);
        assertEquals(GLDataType.typeOf(DataTypes.UINT), GL_UNSIGNED_INT);
    }

    @Test
    public void typeOfGivesZeroWithNullType() {
        assertEquals(GLDataType.typeOf(null), GL_NONE);
    }
}