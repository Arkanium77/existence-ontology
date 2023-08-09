package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

import java.util.Map;

public class MapAbsenceRule implements AbsenceRule {
    @Override
    public boolean applicable(Object o) {
        return o instanceof Map;
    }

    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        Map<?, ?> map = (Map<?, ?>) o;
        if (map.isEmpty()) {
            return true;
        }
        return checker.absent(map.values());
    }
}
