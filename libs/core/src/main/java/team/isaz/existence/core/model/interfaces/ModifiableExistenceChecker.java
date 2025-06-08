package team.isaz.existence.core.model.interfaces;

import team.isaz.existence.core.model.constants.AbsenceInterpretationStrategies;

import java.util.Collection;

/**
 * <h2>ModifiableExistenceChecker</h2>
 * <p>
 * This interface defines a contract for classes that implement existence check functionality with run-time modifiability
 * </p>
 * <p>
 * This interface extends basic {@link ExistenceChecker} with a large group of methods for managing Checkers instances
 * </p>
 */
public interface ModifiableExistenceChecker extends ExistenceChecker {

    /**
     * Method for creating a safe copy
     *
     * @return a fully functional copy of ExistenceChecker without any links
     * to the original and safe for further modification
     */
    ModifiableExistenceChecker copy();

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
    ModifiableExistenceChecker strategy(AbsenceInterpretationStrategy strategy);

    /**
     * <p>Method for registering a new empty check rule</p>
     *
     * @param rule {@link AbsenceRule} that checker will use for all incoming objects
     * @return instance of ExistenceChecker that uses all the rules it previously contained and one new.
     * This can be a new instance or a modified current one
     */
    ModifiableExistenceChecker register(AbsenceRule rule);

    /**
     * <p>Method for registering a new empty check rules</p>
     *
     * @param rules {@link Collection Collection} of {@link AbsenceRule} that checker will use for all incoming objects
     * @return instance of ExistenceChecker that uses all the rules it previously contained and all rules
     * from collection. This can be a new instance or a modified current one
     */
    ModifiableExistenceChecker registerAll(Collection<AbsenceRule> rules);

    /**
     * <p>Method for setup empty check rules</p>
     *
     * @param rules {@link Collection Collection} of {@link AbsenceRule} that checker will use for all incoming objects
     * @return instance of ExistenceChecker that uses only the rules from collection.
     * All previously contained rules will be forgotten. This can be a new instance or a modified current one
     */
    ModifiableExistenceChecker setup(Collection<AbsenceRule> rules);
}
