package ch.texelengine.engine.scenegraph;

/**
 * Represent a scene containing nodes that can be rendered
 *
 * <p>
 * The scene is defined by its root node onto which can be added children
 * that form a tree.
 * </p>
 *
 * @author Dorian Ros
 */
public class Scene {

    /**
     * Root {@link Node} for the scene
     */
    private final Node root;

    /**
     * Construct a new {@link Scene} object
     *
     * @param root the root {@link Node} for the scene
     */
    public Scene(Node root) {
        this.root = root;
    }

    /**
     * Update the scene and all the nodes in it
     */
    public void udpate() {
        this.root.updateAll();
    }
}
