package team.isaz.existence.core.model.interfaces;

import team.isaz.existence.core.model.dto.CheckResult;

public interface AbsenceInterpretationStrategy {
    boolean interpret(CheckResult result);
}
