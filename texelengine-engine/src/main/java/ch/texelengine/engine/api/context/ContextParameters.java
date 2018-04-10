package ch.texelengine.engine.api.context;

/**
 * Represent a set of parameters that define a graphical context
 *
 * <p>
 * This description is API independent
 * </p>
 *
 * @author Dorian Ros
 */
public class ContextParameters {

    /**
     * Version major of the API
     */
    private int versionMajor;

    /**
     * Version minor of the API
     */
    private int versionMinor;

    /**
     * Flag that defines whether the API can throw errors
     *
     * <p>
     * It is recommended to always set this to <code>false</code> or
     * it could cause undefined behaviour
     * </p>
     */
    private boolean noErrors;

    /**
     * Constructs a new {@link ContextParameters} object
     *
     * @param major the version major of the API
     * @param minor the version minor of the API
     * @param noErrors flag that defines whether the API can throw errors
     */
    public ContextParameters(int major, int minor, boolean noErrors) {
        this.versionMajor = major;
        this.versionMinor = minor;
        this.noErrors = noErrors;
    }

    /**
     * Constructs a new {@link ContextParameters} object
     *
     * <p>
     * The {@link #noErrors} parameter is set to <code>false</code> by default
     * </p>
     *
     * @param major the version major of the API
     * @param minor the version minor of the API
     */
    public ContextParameters(int major, int minor) {
        this(major, minor, false);
    }

    /**
     * Get the version major for the API
     *
     * @return the version major
     */
    public int major() {
        return versionMajor;
    }

    /**
     * Get the version minor for the API
     *
     * @return the version minor
     */
    public int minor() {
        return versionMinor;
    }

    /**
     * Get the flag that define whether the API can throw errors
     *
     * @return the error flag
     */
    public boolean noErrors() {
        return noErrors;
    }
}
