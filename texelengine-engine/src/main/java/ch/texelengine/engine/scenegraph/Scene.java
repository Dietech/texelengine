package ch.texelengine.engine.scenegraph;

/**
 *
 * @author Dorian Ros
 */
public class Scene {

    /**
     *
     */
    private final Node root;

    /**
     *
     * @param root
     */
    public Scene(Node root) {
        this.root = root;
    }

    /**
     *
     */
    public void udpate() {
        this.root.updateAll();
    }
}
