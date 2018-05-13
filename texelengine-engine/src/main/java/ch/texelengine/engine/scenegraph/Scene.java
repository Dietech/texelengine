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
        this.root.setScene(this);
    }

    /**
     * Deserialize nodes from a JSON string and add them to the scene
     *
     * @param json the JSON string to deserialize
     */
    public void loadFromJson(String json) {
        this.root.addChildFromJson(json);
    }

    /**
     * Update the scene and all the nodes in it
     */
    public void udpate() {
        this.root.updateAll();
    }

    /**
     * Save <code>this</code> scene to a file
     *
     * @param filename the file path to save the file into
     */
    public void saveTo(String filename) {
        throw new RuntimeException("This function is not yet implemented");
    }
}
