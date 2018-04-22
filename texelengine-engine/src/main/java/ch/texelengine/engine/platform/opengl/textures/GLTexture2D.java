package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.Texture2D;
import ch.texelengine.engine.api.textures.TextureFilter;
import ch.texelengine.engine.api.textures.TextureLoadOptions;
import ch.texelengine.engine.api.textures.WrapMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author Dorian
 */
public class GLTexture2D extends Texture2D {

    /**
     *
     */
    private static int maxTextureUnits = 0;

    /**
     *
     */
    private int currentUnit;

    /**
     *
     * @param width
     * @param height
     * @param filtering
     * @param wrapmode
     */
    public GLTexture2D(int width, int height, TextureFilter filtering, WrapMode wrapmode) {
        this.RID = glGenTextures();
        this.currentUnit = 0;

        glBindTexture(GL_TEXTURE_2D, this.RID);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    /**
     *
     * @param file
     * @param loadOptions
     * @param filtering
     * @param wrapmode
     */
    public GLTexture2D(String file, TextureLoadOptions loadOptions, TextureFilter filtering, WrapMode wrapmode) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(String file, TextureLoadOptions loadOptions) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(int unit) {
        if(maxTextureUnits <= 0 ) {
            maxTextureUnits = glGetInteger(GL_MAX_TEXTURE_UNITS);
        }
        if(unit < 0 || unit >= maxTextureUnits) {
            throw new IllegalArgumentException("Invalid unit parameter. Max value = " + maxTextureUnits);
        }
        glActiveTexture(GL_TEXTURE0 + unit);
        glBindTexture(GL_TEXTURE_2D, this.RID);
        this.currentUnit = unit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        glActiveTexture(GL_TEXTURE0 + currentUnit);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        glDeleteTextures(this.RID);
    }
}
