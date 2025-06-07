package team.isaz.existence.core.model.interfaces;

import team.isaz.existence.core.model.rules.CollectionAbsenceRule;
import team.isaz.existence.core.model.rules.EmptiableAbsenceRule;
import team.isaz.existence.core.model.rules.NoTrimStringAbsenceRule;
import team.isaz.existence.core.model.rules.OptionalAbsenceRule;
import team.isaz.existence.core.model.rules.StringAbsenceRule;

/**
 * <h2>AbsenceRule</h2>
 * <p>
 * This interface defines a contract for classes that describe rules for absence defining.
 * </p>
 * <p>
 * It will be helpful to check out the implementations:
 *     <ul>
 *         <li>
 *             {@link EmptiableAbsenceRule} and {@link OptionalAbsenceRule} - the simplest rules:
 *             An object is empty if it tells us that it is empty
 *         </li>
 *         <li>
 *             {@link NoTrimStringAbsenceRule} and {@link StringAbsenceRule} -
 *             rules checking the same data type ({@link String}).
 *             One uses the standard {@link String#isEmpty} method, and the
 *             other assumes that a string containing only spaces is still empty
 *         </li>
 *         <li>
 *             {@link CollectionAbsenceRule} - a rule that checks not only the collection itself,
 *             but also nested elements of the collection for emptiness
 *         </li>
 *     </ul>
 * </p>
 */
public interface AbsenceRule {

    /**
     * <p>Checking that a rule can be applied to an incoming object</p>
     * <p>
     * In most cases, it is a type check. For example, the rule "if a number is greater than zero, it exists"
     * can be applied only to {@link Number}, but not to {@link String}.
     * </p>
     * <p>However, it is possible to set more complex conditions, depending on business requirements</p>
     *
     * @param o {@link Object} for checking
     * @return {@code true} if rule can be applied for received object. This means that the absence method for
     * this object can be called safely. In other cases {@code false} will be returned
     */
    boolean applicable(Object o);

    /**
     * <p>Checking that incoming object is absent</p>
     * <p>
     * Absence checking can rely on a wide variety of criteria. The simplest way is not to implement a rule,
     * but to implement the {@link Emptiable} interface and use an existing class
     * {@link EmptiableAbsenceRule EmptiableAbsenceRule}.
     * </p>
     * <p>
     * However, in case you cannot implement the interface - you must implement your own rule and describe
     * the logic of the absence check in this method
     * </p>
     *
     * @param o       {@link Object} for checking
     * @param checker {@link ExistenceChecker} which can be used to check nested fields or objects within collections.
     *                Usage is optional
     * @return {@code true} if object is 'absent' by your rule and {@code false} otherwise
     */
    boolean absent(Object o, ExistenceChecker checker);
}
