package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

public class NullAbsenceRule implements AbsenceRule {
    @Override
    public boolean applicable(Object o) {
        return o == null;
    }

    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return o == null;
    }
}
