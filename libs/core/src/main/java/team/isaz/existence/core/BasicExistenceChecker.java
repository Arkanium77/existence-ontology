package team.isaz.existence.core;

import team.isaz.existence.core.model.constants.AbsenceInterpretationStrategies;
import team.isaz.existence.core.model.dto.CheckResult;
import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ModifiableExistenceChecker;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * <h2>BasicExistenceChecker</h2>
 * <p>Basic implementation of ModifiableExistenceChecker</p>
 * <p>Mutable, in control methods returns itself to create a fluent api</p>
 */
public class BasicExistenceChecker implements ModifiableExistenceChecker {

    private final Queue<AbsenceRule> rules;
    private AbsenceInterpretationStrategy strategy;

    /**
     * Creates an empty checker using the {@code ANY_EXISTS} interpretation strategy.
     */
    public BasicExistenceChecker() {
        rules = new ConcurrentLinkedQueue<>();
        strategy = AbsenceInterpretationStrategies.ANY_EXISTS;
    }

    /**
     * Creates a checker with the provided rules and the default
     * {@code ANY_EXISTS} interpretation strategy.
     *
     * @param rules rules to register
     */
    public BasicExistenceChecker(Collection<AbsenceRule> rules) {
        this.rules = new ConcurrentLinkedQueue<>(rules);
        strategy = AbsenceInterpretationStrategies.ANY_EXISTS;
    }

    /**
     * Creates a checker with the provided rules and strategy.
     *
     * @param rules     rules to register
     * @param strategy  strategy used to interpret results
     */
    public BasicExistenceChecker(Collection<AbsenceRule> rules, AbsenceInterpretationStrategy strategy) {
        this.rules = new ConcurrentLinkedQueue<>(rules);
        this.strategy = strategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifiableExistenceChecker copy() {
        return new BasicExistenceChecker(this.rules, this.strategy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifiableExistenceChecker strategy(AbsenceInterpretationStrategy strategy) {
        if (strategy == null) {
            throw new RuntimeException("Null cannot be set as a check strategy");
        }
        this.strategy = strategy;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifiableExistenceChecker register(AbsenceRule rule) {
        rules.add(rule);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifiableExistenceChecker registerAll(Collection<AbsenceRule> rules) {
        this.rules.addAll(rules);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModifiableExistenceChecker setup(Collection<AbsenceRule> rules) {
        if (rules == null) {
            throw new RuntimeException("Null cannot be set as a queue of absent check rules");
        }
        this.rules.clear();
        this.rules.addAll(rules);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o) {
        CheckResult result = rules
                .stream()
                .filter(r -> r.applicable(o))
                .map(r -> r.absent(o, this))
                .collect(Collectors.collectingAndThen(Collectors
                        .groupingBy(t -> t, Collectors.counting()), CheckResult::new));
        return strategy.interpret(result);
    }
}
