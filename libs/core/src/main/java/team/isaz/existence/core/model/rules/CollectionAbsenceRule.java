package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

import java.util.Collection;

/**
 * Checks collections and their elements for absence.
 */
public class CollectionAbsenceRule implements AbsenceRule {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applicable(Object o) {
        return o instanceof Collection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        Collection<?> collection = (Collection<?>) o;
        if (collection.isEmpty()) {
            return true;
        }
        return collection.stream()
                .allMatch(checker::absent);
    }
}
