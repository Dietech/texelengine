package ch.texelengine.engine.scenegraph;

import ch.texelengine.engine.scenegraph.nodes.Spatial;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

import static ch.texelengine.engine.scenegraph.ChangeType.PARENT;
import static ch.texelengine.engine.scenegraph.ChangeType.SCENE;

/**
 * Represent a node in a {@link Scene}
 *
 * <p>
 * This class the base class for every type of node that are
 * present in the engine.
 * A node can be saved to a file and reloaded in the engine in the constructor
 * </p>
 *
 * @author Dorian Ros
 */
public abstract class Node {

    /**
     * Parent node of <code>this</code> node
     */
    protected Node parent;

    /**
     * List of child nodes of <code>this</code> node
     */
    protected List<Node> children;

    /**
     * The {@link Scene} that owns this node
     *
     * <p>
     * Is null if the node is not part of any scene
     * </p>
     */
    protected Scene scene;

    /**
     * Construct a new {@link Node} object
     */
    public Node() {
        this.parent = null;
        this.children = new ArrayList<>();
    }

    /**
     * Update <code>this</code> node
     *
     * <p>
     * This is responsible for updating this node and this node only
     * </p>
     */
    protected abstract void update();

    /**
     * Update <code>this</code> node and all its children
     *
     * <p>
     * This function is recursive so a call to this function from a node
     * automatically updates all of the nodes of the tree below
     * </p>
     */
    protected void updateAll() {
        //Update locally
        update();

        //Update the children
        for (Node child : this.children) {
            child.updateAll();
        }
    }

    /**
     * Add a child node to <code>this</code> node
     *
     * <p>
     * This method automatically set this node as a parent
     * of the child node and set the child's scene object.
     * </p>
     *
     * @param child the child node to add
     */
    public void addChild(Node child) {
        //Update the parenting
        this.children.add(child);
        child.setParent(this);
        //Update the scene object
        if(this.scene != null) {
            child.notifyChanged(SCENE);
        }
        //Notify the child from parent change
        child.notifyChanged(PARENT);
    }

    /**
     * Set the parent of <code>this</code> node
     *
     * @param parent the new parent of this node
     */
    private void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Notify <code>this</code> node and all its children for a state
     * change.
     *
     * <p>
     * This function is recursive so a call to this function from a node
     * automatically notifies all of the nodes of the tree below
     * </p>
     */
    protected abstract void notifyChanged(ChangeType change);

    /**
     * Notify the child nodes of an event type.
     *
     * @param change the type of change event that occurred
     */
    protected void notifyChildren(ChangeType change) {
        for(Node child : this.children) {
            child.notifyChanged(change);
        }
    }

    /**
     * Try to cast <code>this</code> node to a node of a different type.
     *
     * @param type the type of the node to try to cast this node to
     * @param <T> a type that extends the base {@link Node} class
     * @return the casted node or null if the cast is invalid
     */
    public <T extends Node> T castTo(Class<T> type) {
        try {
            return type.cast(this);
        } catch(ClassCastException cce) {
            return null;
        }
    }

    /**
     * Convert <code>this</code> {@link Node} to as JSON string that can be later
     * converted back to a node object.
     *
     * <p>
     * This method also converts the child nodes
     * </p>
     */
    public String writeAsJson() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        JsonElement jsonNode = serializeInternal(gson);
        return gson.toJson(jsonNode);
    }

    /**
     * Add a child {@link Node} reconstructed from a JSON string
     * to <code>this</code> node.
     *
     * @param json the JSON formatted string. Can be obtained with the {@link #writeAsJson()}
     *             method
     */
    public void addChildFromJson(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonNode = parser.parse(json);
        addChild(deserializeGeneric(jsonNode));
    }

    /**
     * Recursive serialization method for <code>this</code>
     * {@link Node} and its children.
     *
     * <p>
     * Internal use only
     * </p>
     *
     * @param gson the json serializer object
     * @return a {@link JsonElement} containing the serialized node
     */
    private JsonElement serializeInternal(Gson gson) {
        JsonObject jsonNode = serialize(gson);
        JsonArray jsonChildren = new JsonArray();
        for(Node child : this.children) {
            jsonChildren.add(child.serializeInternal(gson));
        }
        jsonNode.add("children", jsonChildren);
        return jsonNode;
    }

    /**
     * Serialize <code>this</code> node to a {@link JsonObject} that can be saved
     * as a JSON string.
     *
     * @param gson the json serializer object
     * @return the serialized json object
     */
    public abstract JsonObject serialize(Gson gson);

    /**
     * Convert back a {@link JsonElement} to a node of the correct type.
     *
     * <p>
     * In order for the deserialization to know of which type the node is,
     * a type enum is encoded in the <tt>type</tt> parameter of each
     * serialized node.
     * This method also recursively creates the children of the node
     * </p>
     *
     * @param element the json element to deserialize
     * @return the newly created node
     */
    private Node deserializeGeneric(JsonElement element) {
        JsonObject object = element.getAsJsonObject();
        Node result;
        switch(NodeTypes.valueOf(object.get("type").getAsString())) {
            case SPATIAL:
                result = new Spatial();
                result.deserialize(element);
                break;

            default:
                return null;
        }
        JsonArray children = object.getAsJsonArray("children");
        for(int i = 0; i < children.size(); i++) {
            JsonElement child = children.get(i);
            result.addChild(deserializeGeneric(child));
        }
        return result;
    }

    /**
     * Update <code>this</code> node with the values of the deserialized
     * {@link JsonElement}.
     *
     * <p>
     * This method does not need to bother about the children nodes but only
     * of the specific node type
     * </p>
     *
     * @param jsonElement the JsonElement to get the data from
     */
    public abstract void deserialize(JsonElement jsonElement);

    /**
     * Set the {@link Scene} object of <code>this</code> node
     *
     * @param scene the scene object
     */
    protected void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Get the {@link Scene} that owns <code>this</code> node
     *
     * <p>
     * Can be <code>null</code>
     * </p>
     *
     * @return the scene that owns this node
     */
    public Scene scene() {
        return this.scene;
    }
}
