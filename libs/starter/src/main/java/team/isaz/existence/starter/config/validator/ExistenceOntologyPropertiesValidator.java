package team.isaz.existence.starter.config.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.starter.properties.ExistenceOntologyProperties;

@Component
@RequiredArgsConstructor
public class ExistenceOntologyPropertiesValidator implements SmartInitializingSingleton {

    private final ExistenceOntologyProperties props;
    private final ApplicationContext ctx;

    @Override
    public void afterSingletonsInstantiated() {
        var ruleNames = ctx.getBeansOfType(AbsenceRule.class).keySet();
        var strategyNames = ctx.getBeansOfType(AbsenceInterpretationStrategy.class).keySet();

        if (props.getStrategy() != null && !strategyNames.contains(props.getStrategy())) {
            throw new IllegalArgumentException("Unknown strategy: " + props.getStrategy());
        }

        if (props.getRules() != null) {
            var missing = props.getRules().stream()
                    .filter(name -> !ruleNames.contains(name))
                    .toList();

            if (!missing.isEmpty()) {
                throw new IllegalArgumentException("Unknown AbsenceRule bean names: " + missing);
            }
        }
    }
}