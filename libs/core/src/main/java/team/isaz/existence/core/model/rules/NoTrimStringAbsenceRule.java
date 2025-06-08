package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Considers a string absent only when it is exactly empty.
 */
public class NoTrimStringAbsenceRule implements AbsenceRule {
    @Override
    public boolean applicable(Object o) {
        return o instanceof String;
    }

    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return ((String) o).isEmpty();
    }
}
