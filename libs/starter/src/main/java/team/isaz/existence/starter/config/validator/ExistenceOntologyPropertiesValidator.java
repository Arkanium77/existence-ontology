package team.isaz.existence.starter.config.validator;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.starter.properties.ExistenceOntologyProperties;

/**
 * Validates that configured bean names exist in the context.
 */
@Component
public class ExistenceOntologyPropertiesValidator implements SmartInitializingSingleton {
    private final ApplicationContext ctx;
    private final ExistenceOntologyProperties props;

    /**
     * Autowire constructor
     *
     * @param ctx   source of bean names
     * @param props properties that will be validated
     */
    @Autowired
    public ExistenceOntologyPropertiesValidator(ApplicationContext ctx, ExistenceOntologyProperties props) {
        this.ctx = ctx;
        this.props = props;
    }

    /**
     * Using the default constructor is not allowed
     */
    private ExistenceOntologyPropertiesValidator() {
        throw new AssertionError("Non-instantiable constructor");
    }

    /**
     * Ensure that beans referenced in properties are present.
     */
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