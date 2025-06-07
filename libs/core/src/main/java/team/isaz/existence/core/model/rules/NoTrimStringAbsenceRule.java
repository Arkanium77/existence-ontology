package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Considers a string absents only when it is exactly empty.
 */
public class NoTrimStringAbsenceRule implements AbsenceRule {

    /**
     * Default constructor that does not perform any actions
     */
    public NoTrimStringAbsenceRule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applicable(Object o) {
        return o instanceof String;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return ((String) o).isEmpty();
    }
}
