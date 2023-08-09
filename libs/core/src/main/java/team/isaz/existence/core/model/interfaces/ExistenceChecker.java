package team.isaz.existence.core.model.interfaces;

import team.isaz.existence.core.model.constants.AbsenceInterpretationStrategies;

import java.util.Collection;

/**
 * <h2>ExistenceChecker</h2>
 * <p>
 * This interface defines a contract for classes that implement the existence checking functionality.
 * </p>
 * <p>
 * The interface consists of two large parts
 * <ul>
 *     <li>Technical methods for managing Checkers instances</li>
 *     <li>Methods for checking different existence/absence conditions</li>
 * </ul>
 * </p>
 */
public interface ExistenceChecker {

    /**
     * Method for creating a safe copy
     *
     * @return a fully functional copy of ExistenceChecker without any links
     * to the original and safe for further modification
     */
    ExistenceChecker copy();

    /**
     * <p>Method for selecting a strategy for interpreting the results</p>
     * <br>
     * <p>ExistenceChecker may contain non-consistent rules</p>
     * <p>
     * for example, both work with strings,
     * but one considers only "" to be an empty string,
     * and another considers a string containing only spaces to be empty.
     * </p>
     * <p>This method helps to set a rule to resolve conflicts.</p>
     *
     * @param strategy {@link AbsenceInterpretationStrategy} that can resolve conflicts right way.
     *                 Example of strategies can be found in
     *                 {@link AbsenceInterpretationStrategies AbsenceInterpretationStrategies}
     * @return instance of ExistenceChecker that resolves conflicts according to the chosen strategy.
     * This can be a new instance or a modified current one
     */
    ExistenceChecker strategy(AbsenceInterpretationStrategy strategy);

    /**
     * <p>Method for registering a new empty check rule</p>
     *
     * @param rule {@link AbsenceRule} that checker will use for all incoming objects
     * @return instance of ExistenceChecker that uses all the rules it previously contained and one new.
     * This can be a new instance or a modified current one
     */
    ExistenceChecker register(AbsenceRule rule);

    /**
     * <p>Method for registering a new empty check rules</p>
     *
     * @param rules {@link Collection Collection} of {@link AbsenceRule} that checker will use for all incoming objects
     * @return instance of ExistenceChecker that uses all the rules it previously contained and all rules
     * from collection. This can be a new instance or a modified current one
     */
    ExistenceChecker registerAll(Collection<AbsenceRule> rules);

    /**
     * <p>Method for setup empty check rules</p>
     *
     * @param rules {@link Collection Collection} of {@link AbsenceRule} that checker will use for all incoming objects
     * @return instance of ExistenceChecker that uses only the rules from collection.
     * All previously contained rules will be forgotten. This can be a new instance or a modified current one
     */
    ExistenceChecker setup(Collection<AbsenceRule> rules);


    /**
     * Check if the object is absent
     *
     * @param o {@link Object} for check
     * @return {@code true} if object is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    boolean absent(Object o);

    /**
     * Check if any of objects is absent
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if at least one of objects is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    default boolean anyAbsent(Object... os) {
        for (Object o : os) {
            if (absent(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if all objects is absent
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if all objects is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    default boolean allAbsent(Object... os) {
        for (Object o : os) {
            if (!absent(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the object is existing
     *
     * @param o {@link Object} for check
     * @return {@code true} if object is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    default boolean exists(Object o) {
        return !absent(o);
    }

    /**
     * Check if any of objects is existing
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if at least one of objects is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    default boolean anyExists(Object... os) {
        return !allAbsent(os);
    }

    /**
     * Check if all objects is existing
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if all objects is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    default boolean allExists(Object... os) {
        return !anyAbsent(os);
    }
}
