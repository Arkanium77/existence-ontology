package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

import java.util.Optional;

public class OptionalAbsenceRule implements AbsenceRule {
    @Override
    public boolean applicable(Object o) {
        return o instanceof Optional;
    }

    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        return !((Optional<?>) o).isPresent();
    }
}
