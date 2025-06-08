package team.isaz.existence.core.model.interfaces;

/**
 * <h2>Emptiable</h2>
 * <h3>Some objects may be empty, and this is absolutely normal.</h3>
 * <p>
 * This interface signals that this object is just such an object
 * and allows you to define the rules by which the object
 * will be considered empty
 * </p>
 */
public interface Emptiable {

    /**
     * The method reports information about whether the object is empty.
     * These rules may be different for each business entity.
     *
     * @return {@code true} - if the object is considered empty for your business, and {@code false} otherwise
     */
    boolean empty();
}
