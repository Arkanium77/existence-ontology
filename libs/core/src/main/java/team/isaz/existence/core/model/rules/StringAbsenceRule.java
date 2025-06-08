package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Considers a string with only whitespace characters as absent.
 */
public class StringAbsenceRule implements AbsenceRule {

    /**
     * Default constructor that does not perform any actions
     */
    public StringAbsenceRule() {
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
        return ((String) o).trim().isEmpty();
    }
}
