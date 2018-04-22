package ch.texelengine.engine;

import ch.texelengine.engine.api.context.Context;
import ch.texelengine.engine.api.context.ContextParameters;
import ch.texelengine.engine.api.context.GraphicsAPI;
import ch.texelengine.engine.api.context.WindowParameters;

import static org.lwjgl.glfw.GLFW.*;

public class TestMain {

    public static void main(String[] args) {
        Context ctx = Context.create(GraphicsAPI.OPENGL, new ContextParameters(3, 3), WindowParameters.DEFAULT);

        while(!ctx.window().shouldClose()) {

            ctx.pollEvents();
        }

        ctx.destroyContext();

        Context.destroy();
    }
}
