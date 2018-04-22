package ch.texelengine.engine.platform.opengl.textures;

import ch.texelengine.engine.api.textures.*;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static ch.texelengine.engine.api.textures.TextureLoadOptions.FLIP_Y;
import static ch.texelengine.engine.api.textures.TextureLoadOptions.NONE;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryUtil.NULL;

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
    public GLTexture2D(int width, int height, TextureFormats format, TextureFilters filtering, WrapModes wrapmode) {
        this.currentUnit = 0;
        this.width = width;
        this.height = height;
        this.RID = load(null, null, format, filtering, wrapmode);
    }

    /**
     *
     * @param file
     * @param loadOptions
     * @param filtering
     * @param wrapmode
     */
    public GLTexture2D(String file, TextureLoadOptions loadOptions, TextureFormats format, TextureFilters filtering, WrapModes wrapmode) {
        this.currentUnit = 0;
        this.RID = load(file, loadOptions, format, filtering, wrapmode);
    }

    /**
     *
     * @param file
     * @param loadOptions
     * @param format
     * @param filtering
     * @param wrapmode
     * @return
     */
    private int load(String file, TextureLoadOptions loadOptions, TextureFormats format, TextureFilters filtering, WrapModes wrapmode) {
        int rid = glGenTextures();

        ByteBuffer image = null;
        if(file != null && !file.trim().equals("")) {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer w = stack.mallocInt(1);
                IntBuffer h = stack.mallocInt(1);
                IntBuffer comp = stack.mallocInt(1);

                stbi_set_flip_vertically_on_load(loadOptions == FLIP_Y);

                image = stbi_load(file, w, h, comp, GLTextureFormat.strideOf(format));
                if(image == null) {
                    throw new RuntimeException("Failed to load image: " + file + System.lineSeparator() + stbi_failure_reason());
                }
                this.width = w.get();
                this.height = h.get();
            }
        }

        //Binds the texture
        glBindTexture(GL_TEXTURE_2D, this.RID);

        //Sets the parameters
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GLTextureFilter.mipmapFilterOf(filtering));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GLTextureFilter.filterOf(filtering));

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GLWrapMode.modeOf(wrapmode));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GLWrapMode.modeOf(wrapmode));

        //Create the texture with a certain format and no data
        glTexImage2D(GL_TEXTURE_2D, 0, GLTextureFormat.internalFormatOf(format), this.width, this.height, 0,
                GLTextureFormat.formatOf(format),
                GLTextureFormat.typeOf(format),
                image);

        //Generates mipmap
        glGenerateMipmap(GL_TEXTURE_2D);
        if(GL.getCapabilities().GL_EXT_texture_filter_anisotropic) {
            float amount = Math.min(16f, glGetFloat(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));
            glTexParameterf(GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, amount);
        } else {
            System.err.println("Warning: Anisotropic filtering not supported by the drivers");
        }

        //Unbinds the texture
        glBindTexture(GL_TEXTURE_2D, 0);

        return rid;
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
