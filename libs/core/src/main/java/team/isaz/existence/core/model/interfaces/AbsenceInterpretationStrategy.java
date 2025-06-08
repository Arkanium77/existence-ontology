package team.isaz.existence.core.model.interfaces;

import team.isaz.existence.core.model.dto.CheckResult;

/**
 * <h2>AbsenceInterpretationStrategy</h2>
 * <p>
 * This interface defines a contract for classes that describe strategies for interpretation results of absence check.
 * </p>
 */
@FunctionalInterface
public interface AbsenceInterpretationStrategy {

    /**
     * Method of interpreting absence check results
     *
     * @param result the result of performing the check. Contains information about
     *               the number of positive and negative triggers of checks
     * @return {@code true} if by this strategy result is 'absent' and {@code false} otherwise
     */
    boolean interpret(CheckResult result);
}
