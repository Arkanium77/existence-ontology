package team.isaz.existence.core.model.interfaces;

public interface InlineAbsenceRule extends AbsenceRule {
    default boolean applicable(Object o) {
        return true;
    }
}
