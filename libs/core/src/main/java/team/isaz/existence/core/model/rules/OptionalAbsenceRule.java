package team.isaz.existence.core.model.rules;

import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

import java.util.Optional;

/**
 * Handles {@link Optional} values and delegates to nested objects.
 */
public class OptionalAbsenceRule implements AbsenceRule {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applicable(Object o) {
        return o instanceof Optional;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o, ExistenceChecker checker) {
        Optional<?> optional = ((Optional<?>) o);
        //noinspection OptionalIsPresent
        if (!optional.isPresent()) {
            return true;
        }

        return checker.absent(optional.get());
    }
}
