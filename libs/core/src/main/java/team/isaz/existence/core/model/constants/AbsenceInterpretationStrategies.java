package team.isaz.existence.core.model.constants;

import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;

/**
 * Common strategies used to interpret the results of absence checks.
 * <p>
 * {@code ANY_EXISTS} treats a value as absent if no rule reports it exists,
 * while {@code ANY_ABSENT} treats a value as absent if at least one rule
 * reports absence.
 */
public class AbsenceInterpretationStrategies {

    private AbsenceInterpretationStrategies() {
        throw new AssertionError("Instantiation not allowed");
    }

    /**
     * ANY_EXISTS strategy
     * <p>
     * An object is considered absent if NO RULE has determined it EXISTING
     */
    public static final AbsenceInterpretationStrategy ANY_EXISTS = r -> {
        if (r.noResults()) {
            return true;
        }
        return r.existsResults() == 0;
    };

    /**
     * ANY_ABSENT strategy
     * <p>
     * An object is considered missing if AT LEAST ONE rule has determined it ABSENCE
     */
    public static final AbsenceInterpretationStrategy ANY_ABSENT = r -> {
        if (r.noResults()) {
            return true;
        }
        return r.absentResults() != 0;
    };
}
