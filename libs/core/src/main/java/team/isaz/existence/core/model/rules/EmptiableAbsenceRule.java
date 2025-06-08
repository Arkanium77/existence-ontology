package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.Emptiable;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Uses {@link Emptiable#empty()} to determine absence.
 */
public class EmptiableAbsenceRule implements AbsenceRule {
    @Override
    public boolean applicable(Object o) {
        return o instanceof Emptiable;
    }

    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return ((Emptiable) o).empty();
    }
}
