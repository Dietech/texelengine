package ch.texelengine.engine.scenegraph.nodes;

import ch.texelengine.engine.scenegraph.ChangeType;
import ch.texelengine.engine.scenegraph.Node;
import ch.texelengine.math.linearalgebra.Matrix4f;
import ch.texelengine.math.linearalgebra.Transform;
import ch.texelengine.math.linearalgebra.Vector3f;

import static ch.texelengine.engine.scenegraph.ChangeType.TRANSFORM;

/**
 *
 * @author Dorian Ros
 */
public class Spatial extends Node {

    /**
     *
     */
    private Transform local;

    /**
     *
     */
    private Matrix4f globalTransformation;

    /**
     *
     */
    private boolean localDirty;

    /**
     *
     */
    private boolean globalDirty;

    /**
     *
     */
    public Spatial() {
        super();
        this.local = new Transform();
        this.globalTransformation = new Matrix4f();
        this.globalDirty = true;
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
            default:
                notifyChildren(change);
                break;
        }
    }

    /**
     *
     * @return
     */
    protected Matrix4f localTransformation() {
        if(this.localDirty) {
            this.local.calculateTransformation();
            this.localDirty = false;
        }
        return this.local.transform();
    }

    /**
     *
     * @return
     */
    protected Matrix4f globalTransformation() {
        if(this.globalDirty) {
            //Try and cast the parent node to a spatial
            Spatial spatialParent = getSpatialParent();
            if(spatialParent != null) {
                //The parent is a spatial and thus have a global transform
                spatialParent.globalTransformation().mul(this.localTransformation(), this.globalTransformation);
            } else {
                //Else the global transformation is a copy of the local transformation
                this.globalTransformation.set(this.localTransformation());
            }
            globalDirty = false;
        }
        return this.globalTransformation;
    }

    /**
     *
     * @param offset
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
     *
     * @param axis
     * @param angle
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
     *
     * @param scaling
     */
    public void scale(Vector3f scaling) {
        this.local.scale(scaling.x(), scaling.y(), scaling.z());
        //If children not already notified
        if(!this.localDirty) {
            this.localDirty = true;
            notifyChildren(TRANSFORM);
        }
    }

    /**
     *
     * @return
     */
    private Spatial getSpatialParent() {
        if(this.parent != null) {
            return this.parent.castTo(Spatial.class);
        } else {
            return null;
        }
    }
}
