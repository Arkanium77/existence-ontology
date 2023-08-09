package team.isaz.existence.core.model.constants;

import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;

public class AbsenceInterpretationStrategies {

    public static final AbsenceInterpretationStrategy ANY_EXISTS = r -> {
        if (r.noResults()) {
            return true;
        }
        return r.existsResults() == 0;
    };
    public static final AbsenceInterpretationStrategy ANY_ABSENT = r -> {
        if (r.noResults()) {
            return true;
        }
        return r.absentResults() != 0;
    };
}
