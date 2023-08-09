package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

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
