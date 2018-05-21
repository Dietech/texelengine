package ch.texelengine.engine.api.shaders;

import java.util.EnumMap;
import java.util.Map;

/**
 * {@author Dorian Ros}
 */
public class ShaderSource {

    /**
     *
     */
    private Map<ShaderTypes, String> sources;

    /**
     *
     */
    public ShaderSource() {
        this.sources = new EnumMap<ShaderTypes, String>(ShaderTypes.class);
    }

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
     *
     * @param source
     * @param type
     */
    public void addShaderSource(String source, ShaderTypes type) {
        this.sources.put(type, source);
    }

    /**
     *
     * @param type
     * @return
     */
    public boolean hasSource(ShaderTypes type) {
        return this.sources.containsKey(type);
    }

    /**
     *
     * @param type
     * @return
     */
    public String getSource(ShaderTypes type) {
        return this.sources.get(type);
    }

    /**
     *
     * @return
     */
    public Map<ShaderTypes, String> sources() {
        return this.sources;
    }
}