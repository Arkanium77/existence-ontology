package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Treats {@code null} values as absent.
 */
public class NullAbsenceRule implements AbsenceRule {

    /**
     * Default constructor that does not perform any actions
     */
    public NullAbsenceRule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applicable(Object o) {
        return o == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return o == null;
    }
}
