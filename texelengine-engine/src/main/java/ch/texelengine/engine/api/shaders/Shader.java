package ch.texelengine.engine.api.shaders;

import ch.texelengine.engine.platform.opengl.shaders.GLShader;

import java.util.Map;

/**
 * @author Dorian Ros
 */
public abstract class Shader {

    /**
     *
     */
    protected int RID;

    /**
     *
     */
    private ShaderSource source;

    /**
     *
     */
    protected Shader() {
        this.source = new ShaderSource();
    }

    /**
     *
     * @param source
     * @return
     */
    public static Shader create(String source) {
        //TODO: switch with context API
        Shader shader = new GLShader();
        shader.setSource(source);
        shader.compileSource();
        shader.validate();
        return shader;
    }

    /**
     *
     * @param source
     */
    private void setSource(String source) {
        this.source.setFromSource(source);
    }

    /**
     *
     */
    private void compileSource() {
        for(Map.Entry<ShaderTypes, String> entry : this.source.sources().entrySet()) {
            addShader(entry.getValue(), entry.getKey());
        }
    }

    /**
     *
     * @param source
     * @param type
     */
    protected abstract void addShader(String source, ShaderTypes type) throws RuntimeException;

    /**
     *
     */
    protected abstract void validate() throws RuntimeException;

    /**
     *
     */
    public abstract void bind();

    /**
     *
     */
    public abstract void unbind();

    /**
     *
     */
    public abstract void destroy();
}
