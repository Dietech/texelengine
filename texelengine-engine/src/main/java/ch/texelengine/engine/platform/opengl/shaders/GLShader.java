package ch.texelengine.engine.platform.opengl.shaders;

import ch.texelengine.engine.api.shaders.Shader;
import ch.texelengine.engine.api.shaders.ShaderTypes;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Represents an OpenGL shader object
 *
 * <p>
 * This is the implementation of the {@link Shader} class for the OpenGL platform
 * </p>
 *
 * @author Dorian Ros
 */
public class GLShader extends Shader {

    /**
     * List of the currently created shader stages
     *
     * <p>
     * This list is cleared when the different shader stage are deleted
     * which happens whenever the program is linked and validated
     * </p>
     */
    private List<Integer> shaderIDs;

    /**
     * Construct a new {@link GLShader} and set its {@link #RID}
     */
    public GLShader() {
        super();

        this.shaderIDs = new ArrayList<>();
        this.RID = glCreateProgram();

        if(this.RID == 0) {
            throw new RuntimeException("Could not create shader program");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void compileShader(String source, ShaderTypes type) throws RuntimeException {
        int shaderID = glCreateShader(GLShaderType.typeOf(type));

        if(shaderID == 0) {
            throw new RuntimeException("Could not create shader");
        }

        //Set the source and compile
        glShaderSource(shaderID, source);
        glCompileShader(shaderID);

        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException(String.format("Shader compilation failed: %s", glGetShaderInfoLog(shaderID, 1024)));
        }

        //Attach the shader to the program
        glAttachShader(this.RID, shaderID);

        //Register shader id for later deletion
        this.shaderIDs.add(shaderID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validate() throws RuntimeException {
        glLinkProgram(this.RID);

        if(glGetProgrami(this.RID, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException(String.format("Shader program link error: %s", glGetProgramInfoLog(this.RID, 1024)));
        }

        glValidateProgram(this.RID);

        if(glGetProgrami(this.RID, GL_VALIDATE_STATUS) == GL_FALSE) {
            throw new RuntimeException(String.format("Shader program link error: %s", glGetProgramInfoLog(this.RID, 1024)));
        }

        for(Integer id : this.shaderIDs) {
            glDetachShader(this.RID, id);
            glDeleteShader(id);
        }
        this.shaderIDs.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind() {
        glUseProgram(this.RID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        glUseProgram(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        glDeleteProgram(this.RID);
    }
}
