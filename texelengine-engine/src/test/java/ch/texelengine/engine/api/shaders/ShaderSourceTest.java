package ch.texelengine.engine.api.shaders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShaderSourceTest {

    @Test
    public void ParseFailOnInvalidDelimiter() {
        String source = "#shader";
        String source2 = "#shader vertex\n#shader";

        assertThrows(IllegalArgumentException.class, () -> {
            ShaderSource shader = new ShaderSource();
            shader.setFromSource(source);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ShaderSource shader = new ShaderSource();
            shader.setFromSource(source2);
        });
    }

    @Test
    public void ParserWorksWithEveryDelimiter() {
        String source = "foo\n#shader vertex\nfoo1\n#shader fragment\nfoo2\n#shader compute\nfoo3\n#shader geometry\nfoo4";
        ShaderSource shader = new ShaderSource();
        shader.setFromSource(source);
        assertEquals("foo1", shader.getSource(ShaderTypes.VERTEX).trim());
        assertEquals("foo2", shader.getSource(ShaderTypes.FRAGMENT).trim());
        assertEquals("foo3", shader.getSource(ShaderTypes.COMPUTE).trim());
        assertEquals("foo4", shader.getSource(ShaderTypes.GEOMETRY).trim());
    }

    @Test
    public void ParserSupportsOverrides() {
        String source = "foo\n#shader vertex\nfoo1\n#shader vertex\nfoo3";
        ShaderSource shader = new ShaderSource();
        shader.setFromSource(source);
        assertEquals("foo3", shader.getSource(ShaderTypes.VERTEX).trim());
    }
}
