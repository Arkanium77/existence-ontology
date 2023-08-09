package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.Emptyable;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

public class EmptyableAbsenceRule implements AbsenceRule {
    @Override
    public boolean applicable(Object o) {
        return o instanceof Emptyable;
    }

    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return ((Emptyable) o).empty();
    }
}
