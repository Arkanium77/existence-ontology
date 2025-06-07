package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

import java.util.Map;

/**
 * Considers a map absent if it is empty or all values are absent.
 */
public class MapAbsenceRule implements AbsenceRule {

    /**
     * Default constructor that does not perform any actions
     */
    public MapAbsenceRule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applicable(Object o) {
        return o instanceof Map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        Map<?, ?> map = (Map<?, ?>) o;
        if (map.isEmpty()) {
            return true;
        }
        return checker.absent(map.values());
    }
}
