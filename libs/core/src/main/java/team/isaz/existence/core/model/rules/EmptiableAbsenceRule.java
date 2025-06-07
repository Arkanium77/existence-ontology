package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.Emptiable;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Uses {@link Emptiable#empty()} to determine absence.
 */
public class EmptiableAbsenceRule implements AbsenceRule {

    /**
     * Default constructor that does not perform any actions
     */
    public EmptiableAbsenceRule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applicable(Object o) {
        return o instanceof Emptiable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return ((Emptiable) o).empty();
    }
}
