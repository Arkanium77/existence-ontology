package team.isaz.existence.core.model.interfaces;

import java.util.Collection;

public interface ExistenceChecker {
    ExistenceChecker copy();


    ExistenceChecker strategy(AbsenceInterpretationStrategy strategy);

    ExistenceChecker register(AbsenceRule rule);

    ExistenceChecker registerAll(Collection<AbsenceRule> rules);

    ExistenceChecker setup(Collection<AbsenceRule> rules);


    boolean absent(Object o);

    default boolean anyAbsent(Object... os) {
        for (Object o : os) {
            if (absent(o)) {
                return true;
            }
        }
        return false;
    }

    default boolean allAbsent(Object... os) {
        for (Object o : os) {
            if (!absent(o)) {
                return false;
            }
        }
        return true;
    }

    default boolean allExists(Object... os) {
        return !anyAbsent(os);
    }

    default boolean anyExists(Object... os) {
        return !allAbsent(os);
    }

    default boolean exists(Object o) {
        return !absent(o);
    }
}
