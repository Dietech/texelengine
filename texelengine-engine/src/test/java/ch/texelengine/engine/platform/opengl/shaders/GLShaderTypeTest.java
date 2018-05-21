package ch.texelengine.engine.platform.opengl.shaders;

import ch.texelengine.engine.api.shaders.ShaderTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;
import static org.lwjgl.opengl.GL43.GL_COMPUTE_SHADER;

public class GLShaderTypeTest {

    @Test
    public void IdentifierAreCorrect() {
        assertEquals(GL_VERTEX_SHADER, GLShaderType.typeOf(ShaderTypes.VERTEX));
        assertEquals(GL_FRAGMENT_SHADER, GLShaderType.typeOf(ShaderTypes.FRAGMENT));
        assertEquals(GL_COMPUTE_SHADER, GLShaderType.typeOf(ShaderTypes.COMPUTE));
        assertEquals(GL_GEOMETRY_SHADER, GLShaderType.typeOf(ShaderTypes.GEOMETRY));
    }

    @Test
    public void ReturnsIfInvalid() {
        assertEquals(0, GLShaderType.typeOf(null));
        assertEquals(0, GLShaderType.typeOf(ShaderTypes.NONE));
    }
}
