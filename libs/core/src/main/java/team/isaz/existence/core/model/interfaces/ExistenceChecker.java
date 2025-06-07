package team.isaz.existence.core.model.interfaces;

/**
 * <h2>ExistenceChecker</h2>
 * <p>
 * This interface defines a contract for classes that implement the existence checking functionality.
 * </p>
 * <p>
 * The interface consists methods for checking different existence/absence conditions
 * </p>
 */
public interface ExistenceChecker {

    /**
     * Check if the object is absent
     *
     * @param o {@link Object} for check
     * @return {@code true} if object is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    boolean absent(Object o);

    /**
     * Check if any of the objects is absent
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if at least one of objects is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    default boolean anyAbsent(Object... os) {
        for (Object o : os) {
            if (absent(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if all objects are absent
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if all objects is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    default boolean allAbsent(Object... os) {
        for (Object o : os) {
            if (!absent(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the object is existing
     *
     * @param o {@link Object} for check
     * @return {@code true} if an object is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    default boolean exists(Object o) {
        return !absent(o);
    }

    /**
     * Check if any of objects is existing
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if at least one of objects is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    default boolean anyExists(Object... os) {
        return !allAbsent(os);
    }

    /**
     * Check if all objects are existing
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if all objects is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    default boolean allExists(Object... os) {
        return !anyAbsent(os);
    }
}
