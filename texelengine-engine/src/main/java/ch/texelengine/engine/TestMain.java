package ch.texelengine.engine;

import ch.texelengine.engine.api.context.Context;
import ch.texelengine.engine.api.context.ContextParameters;
import ch.texelengine.engine.api.context.GraphicsAPI;
import ch.texelengine.engine.api.context.WindowParameters;
import ch.texelengine.engine.api.textures.*;

import static org.lwjgl.glfw.GLFW.*;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        Context ctx = Context.create(GraphicsAPI.OPENGL, new ContextParameters(3, 3), WindowParameters.DEFAULT);

        Texture2D tex = Texture2D.create("a", TextureLoadOptions.FLIP_Y, TextureFormats.RGBA16F, TextureFilters.LINEAR, WrapModes.CLAMP);

        while(!ctx.window().shouldClose()) {

            ctx.pollEvents();

            System.out.println(tex.height());

            Thread.sleep(15);
        }

        ctx.destroyContext();

        Context.destroy();
    }
}
