package team.isaz.existence.starter.components;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.dto.CheckResult;
import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;
import team.isaz.existence.starter.properties.ExistenceOntologyProperties;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ExistenceChecker that uses configured beans for rules and strategy.
 */
@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@ConditionalOnMissingBean(ExistenceChecker.class)
public class BeanBasedExistenceChecker implements ExistenceChecker {

    private final List<AbsenceRule> rules;
    private final AbsenceInterpretationStrategy strategy;

    /**
     * Constructs the checker using beans from the Spring context.
     */
    @Autowired
    public BeanBasedExistenceChecker(
            Map<String, AbsenceRule> ruleBeans,
            Map<String, AbsenceInterpretationStrategy> strategies,
            ExistenceOntologyProperties props
    ) {
        this.rules = props.getRules() == null ? Collections.emptyList()
                : props.getRules().stream()
                .map(ruleBeans::get)
                .filter(Objects::nonNull)
                .toList();

        this.strategy = Optional.ofNullable(strategies.get(props.getStrategy()))
                .orElseThrow(() -> new IllegalArgumentException("Unknown strategy: " + props.getStrategy()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean absent(Object o) {
        var result = rules.stream()
                .filter(r -> r.applicable(o))
                .map(r -> r.absent(o, this))
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(x -> x, Collectors.counting()),
                        CheckResult::new
                ));
        return strategy.interpret(result);
    }
}
