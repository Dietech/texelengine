package ch.texelengine.engine.scenegraph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dorian Ros
 */
public abstract class Node {

    /**
     *
     */
    private Node parent;

    /**
     *
     */
    private List<Node> children;

    /**
     *
     */
    private boolean changed;

    /**
     *
     */
    public Node() {
        this.parent = null;
        this.children = new ArrayList<>();
        this.changed = true;
    }

    /**
     *
     */
    public abstract void update();

    /**
     *
     */
    protected void updateAll() {
        update();
        this.changed = false;

        for (Node child : this.children) {
            child.updateAll();
        }
    }

    /**
     *
     */
    protected void notifyChanged() {
        this.changed = true;

        for(Node child : this.children) {
            child.notifyChanged();
        }
    }
}
