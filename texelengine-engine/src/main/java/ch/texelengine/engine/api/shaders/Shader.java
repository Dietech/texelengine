package ch.texelengine.engine.api.shaders;

import ch.texelengine.engine.platform.opengl.shaders.GLShader;

import java.util.Map;

/**
 * Represent a shader program that is stored on the GPU and has a RID
 *
 * <p>
 * This description is API independent. See the API specific implementations for more information about
 * the usage of the class
 * </p>
 *
 * @see GLShader
 *
 * @author Dorian Ros
 */
public abstract class Shader {

    /**
     * Unique identifier of <code>this</code> shader in the current context
     */
    protected int RID;

    /**
     * Source code of <code>this</code> shader program
     */
    private ShaderSource source;

    /**
     * Construct a new {@link Shader} object
     *
     * <p>
     * This is just used for initialization and the {@link #create(String)} method is used
     * to create the actual shader
     * </p>
     */
    protected Shader() {
        this.source = new ShaderSource();
    }

    /**
     * Create a new {@link Shader} from a source code string. This function is API
     * specific.
     *
     * @param source the source code of the shader as a string with "\n" delimiters
     * @return the newly created and compiled shader program
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
     * Set the source code of <code>this</code> shader object
     *
     * <p>
     * This is only used at creation. Later modification of the source code
     * will not affect the shader
     * </p>
     *
     * @param source the source code string with "\n" delimiters
     */
    private void setSource(String source) {
        this.source.setFromSource(source);
    }

    /**
     * Compile the source code into different shaders that are bound to <code>this</code>
     * shader program
     */
    private void compileSource() {
        for(Map.Entry<ShaderTypes, String> entry : this.source.sources().entrySet()) {
            compileShader(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Compile a shader and bind it to <code>this</code> shader program
     *
     * @param source the source code string of the shader
     * @param type the type of the shader that is compiled
     */
    protected abstract void compileShader(String source, ShaderTypes type) throws RuntimeException;

    /**
     * Link and validate <code>this</code> shader program
     */
    protected abstract void validate() throws RuntimeException;

    /**
     * Bind <code>this</code> shader program
     */
    public abstract void bind();

    /**
     * Unbind <code>this</code> shader program if bound
     */
    public abstract void unbind();

    /**
     * Destroy <code>this</code> shader program
     */
    public abstract void destroy();
}
