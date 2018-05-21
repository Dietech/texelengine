package ch.texelengine.engine.platform.opengl.shaders;

import ch.texelengine.engine.api.shaders.Shader;
import ch.texelengine.engine.api.shaders.ShaderTypes;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

/**
 * @author Dorian Ros
 */
public class GLShader extends Shader {

    /**
     *
     */
    private List<Integer> shaderIDs;

    /**
     *
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
    protected void addShader(String source, ShaderTypes type) throws RuntimeException {
        int shaderID = glCreateShader(GLShaderType.typeOf(type));

        if(shaderID == 0) {
            throw new RuntimeException("Could not create shader");
        }

        //Set the source and compile
        glShaderSource(shaderID, source);
        glCompileShader(shaderID);

        if(glGetShaderi(shaderID, GL_COMPILE_STATUS) == 0) {
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

        if(glGetProgrami(this.RID, GL_LINK_STATUS) == 0) {
            throw new RuntimeException(String.format("Shader program link error: %s", glGetProgramInfoLog(this.RID, 1024)));
        }

        glValidateProgram(this.RID);

        if(glGetProgrami(this.RID, GL_VALIDATE_STATUS) == 0) {
            throw new RuntimeException(String.format("Shader program link error: %s", glGetProgramInfoLog(this.RID, 1024)));
        }

        for(Integer id : this.shaderIDs) {
            glDetachShader(this.RID, id);
            glDeleteShader(id);
        }
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
