package team.isaz.existence.core.model.interfaces;

/**
 * Convenience interface for inline absence rules implemented via lambda.
 */
public interface InlineAbsenceRule extends AbsenceRule {
    default boolean applicable(Object o) {
        return true;
    }
}
