package team.isaz.existence.core.model.interfaces;

/**
 * Convenience interface for inline absence rules implemented via lambda.
 */
@FunctionalInterface
public interface InlineAbsenceRule extends AbsenceRule {
    /**
     * Always applicable for any object. Allows rules to be
     * implemented using a single lambda expression.
     *
     * @param o object for applicability check
     * @return always {@code true}
     */
    default boolean applicable(Object o) {
        return true;
    }
}
