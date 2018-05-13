package ch.texelengine.engine.scenegraph.nodes;

import ch.texelengine.engine.scenegraph.ChangeType;
import ch.texelengine.engine.scenegraph.Node;
import ch.texelengine.math.linearalgebra.Matrix4f;
import ch.texelengine.math.linearalgebra.Transform;
import ch.texelengine.math.linearalgebra.Vector3f;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static ch.texelengine.engine.scenegraph.ChangeType.TRANSFORM;
import static ch.texelengine.engine.scenegraph.NodeTypes.SPATIAL;

/**
 * Represent a node that stores a transformation in the 3D world
 *
 * <p>
 * Can be inherited by every node that needs a transformation
 * </p>
 *
 * @author Dorian Ros
 */
public class Spatial extends Node {

    /**
     * Local {@link Transform} object
     */
    private Transform local;

    /**
     * Stores the global transformation matrix (also called <tt>model matrix</tt>)
     */
    private Matrix4f globalTransformation;

    /**
     * Flag that defines if the local transformation is dirty and
     * needs to be reevaluated
     */
    private boolean localDirty;

    /**
     * Flag that defines if the global transformation is dirty and
     * needs to be reevaluated
     */
    private boolean globalDirty;

    /**
     * Parent casted as a {@link Spatial}
     */
    private Spatial spatialParent;

    /**
     * Construct a new {@link Spatial} node with his local transformation set
     * as the identity
     */
    public Spatial() {
        super();
        this.local = new Transform();
        this.globalTransformation = new Matrix4f();
        this.globalDirty = true;
        this.spatialParent = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void update() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void notifyChanged(ChangeType change) {
        switch(change) {
            case TRANSFORM:
                this.globalDirty = true;
                //If the children have not already been notified
                if(!this.localDirty) {
                    notifyChildren(change);
                }
                break;
            case PARENT:
                this.spatialParent = this.parent.castTo(Spatial.class);
                break;
            default:
                notifyChildren(change);
                break;
        }
    }

    @Override
    public JsonElement serialize(Gson gson) {
        JsonObject jsonSpatial = new JsonObject();
        jsonSpatial.add("type", gson.toJsonTree(SPATIAL));
        jsonSpatial.add("position", gson.toJsonTree(this.local.position()));
        jsonSpatial.add("rotation", gson.toJsonTree(this.local.rotation()));
        jsonSpatial.add("scale", gson.toJsonTree(this.local.scale()));
        JsonArray children = new JsonArray();
        for(Node child : this.children) {
            children.add(child.serialize(gson));
        }
        jsonSpatial.add("children", children);
        return jsonSpatial;
    }

    @Override
    public Node deserialize(JsonElement jsonElement) {
        return null;
    }

    /**
     * Get the local transformation matrix of <code>this</code> node
     *
     * <p>
     * This transformation is relative to the parent node
     * </p>
     *
     * @return the local transformation matrix
     */
    protected Matrix4f localTransformation() {
        if(this.localDirty) {
            this.local.calculateTransformation();
            this.localDirty = false;
        }
        return this.local.transform();
    }

    /**
     * Get the global transformation of <code>this</code> node (also called <tt></tt>model matrix</tt>) in the world
     *
     * <p>
     * If the global transformation is dirty then it will be reevaluated by
     * calling the same function on the parent node.
     * If the parent node is not a spatial then the local transformation is
     * used instead (assumes a parent node at the origin of the world)
     * </p>
     *
     * @return the global transformation matrix
     */
    protected Matrix4f globalTransformation() {
        if(this.globalDirty) {
            //If parent is a spatial
            if(this.spatialParent != null) {
                //The parent is a spatial and thus have a global transform
                this.spatialParent.globalTransformation().mul(this.localTransformation(), this.globalTransformation);
            } else {
                //Else the global transformation is a copy of the local transformation
                this.globalTransformation.set(this.localTransformation());
            }
            globalDirty = false;
        }
        return this.globalTransformation;
    }

    /**
     * Move <code>this</code> node by an offset
     *
     * @param offset the offset vector as a {@link Vector3f}
     */
    public void move(Vector3f offset) {
        this.local.move(offset.x(), offset.y(), offset.z());
        //If children not already notified
        if(!this.localDirty) {
            this.localDirty = true;
            notifyChildren(TRANSFORM);
        }
    }

    /**
     * Rotate <code>this</code> node around an axis
     *
     * @param axis the axis to rotate about as a {@link Vector3f}
     * @param angle the angle of the rotation in degrees
     */
    public void rotate(Vector3f axis, float angle) {
        this.local.rotate(axis, angle);
        //If children not already notified
        if(!this.localDirty) {
            this.localDirty = true;
            notifyChildren(TRANSFORM);
        }
    }

    /**
     * Scale <code>this</code> spatial node by a factor vector
     *
     * @param scaling the vector of factors to scale by as a {@link Vector3f}
     */
    public void scale(Vector3f scaling) {
        this.local.scale(scaling.x(), scaling.y(), scaling.z());
        //If children not already notified
        if(!this.localDirty) {
            this.localDirty = true;
            notifyChildren(TRANSFORM);
        }
    }
}
