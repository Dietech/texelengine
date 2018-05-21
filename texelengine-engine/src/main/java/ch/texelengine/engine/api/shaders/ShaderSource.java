package ch.texelengine.engine.api.shaders;

import java.util.EnumMap;
import java.util.Map;

/**
 * Represent a structure containing the source code of a shader
 *
 * <p>
 * This is used in the {@link Shader} class to provide a simple way to parse
 * the shader code into the multiple shader stages
 * </p>
 *
 * {@author Dorian Ros}
 */
public class ShaderSource {

    /**
     * Map of parsed source code and shader stage type
     */
    private Map<ShaderTypes, String> sources;

    /**
     * Construct a new {@link ShaderSource}
     */
    public ShaderSource() {
        this.sources = new EnumMap<ShaderTypes, String>(ShaderTypes.class);
    }

    /**
     * Parse the source code for the different shader stages and store the results
     * in the {@link #sources} map
     *
     * @param source the source code of the whole shader
     * @throws IllegalArgumentException a shader delimiter is invalid
     */
    public void setFromSource(String source) throws IllegalArgumentException {
        String[] lines = source.split("\\r?\\n");

        StringBuilder shader = new StringBuilder();
        ShaderTypes type = ShaderTypes.NONE;
        for(String line : lines) {
            if(line.contains("#shader")) { //TODO: May get delimiter from ShaderBuilder in the future
                if(type != ShaderTypes.NONE) {
                    addShaderSource(shader.toString(), type);
                }
                shader.setLength(0);

                if(line.contains("vertex")) {
                    type = ShaderTypes.VERTEX;
                }
                else if(line.contains("fragment")) {
                    type = ShaderTypes.FRAGMENT;
                }
                else if(line.contains("compute")) {
                    type = ShaderTypes.COMPUTE;
                }
                else if(line.contains("geometry")) {
                    type = ShaderTypes.GEOMETRY;
                }
                else {
                    throw new IllegalArgumentException(String.format("Invalid shader type declaration: %s", line));
                }
            }
            else {
                shader.append(line + "\n");
            }
        }
        if(type != ShaderTypes.NONE) {
            addShaderSource(shader.toString(), type);
        }
    }

    /**
     * Manually add source code for a certain shader stage
     *
     * <p>
     * Replaces the current shader code for the stage
     * </p>
     *
     * @param source the source code string of the shader stage
     * @param type the shader stage type
     */
    public void addShaderSource(String source, ShaderTypes type) {
        this.sources.put(type, source);
    }

    /**
     * Check if <code>this</code> shader has source code for a certain stage
     *
     * @param type shader stage type to check against
     * @return whether the shader has source code for the stage type
     */
    public boolean hasSource(ShaderTypes type) {
        return this.sources.containsKey(type);
    }

    /**
     * Get the source code for a certain shader stage type
     *
     * @param type the type to get the source code from
     * @return the source code string
     */
    public String getSource(ShaderTypes type) {
        return this.sources.get(type);
    }

    /**
     * Get the {@link #sources} parameter of <code>this</code> shader source
     *
     * @return the map of sources and stage types
     */
    public Map<ShaderTypes, String> sources() {
        return this.sources;
    }
}