package ch.texelengine.engine.scenegraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a node in a {@link Scene}
 *
 * <p>
 * This class the base class for every type of node that are
 * present in the engine.
 * </p>
 *
 * @author Dorian Ros
 */
public abstract class Node {

    /**
     * Parent node of <code>this</code> node
     */
    private Node parent;

    /**
     * List of child nodes of <code>this</code> node
     */
    private List<Node> children;

    /**
     * Flag that defines if the node needs to be updated
     */
    private boolean changed;

    /**
     * Construct a new {@link Node} object
     */
    public Node() {
        this.parent = null;
        this.children = new ArrayList<>();
        this.changed = true;
    }

    /**
     * Update <code>this</code> node
     *
     * <p>
     * This is responsible for updating this node and this node only
     * </p>
     */
    public abstract void update();

    /**
     * Update <code>this</code> node and all its children
     *
     * <p>
     * This function is recursive so a call to this function from a node
     * automatically updates all of the nodes of the tree below
     * </p>
     */
    protected void updateAll() {
        update();
        this.changed = false;

        for (Node child : this.children) {
            child.updateAll();
        }
    }

    /**
     * Notify <code>this</code> node and all its children for a state
     * change
     *
     * <p>
     * This function is recursive so a call to this function from a node
     * automatically notifies all of the nodes of the tree below
     * </p>
     */
    protected void notifyChanged() {
        this.changed = true;

        for(Node child : this.children) {
            child.notifyChanged();
        }
    }
}
