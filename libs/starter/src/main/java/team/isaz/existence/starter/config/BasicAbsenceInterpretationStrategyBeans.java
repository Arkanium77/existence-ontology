package team.isaz.existence.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.constants.AbsenceInterpretationStrategies;
import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;

/**
 * Provides default {@link AbsenceInterpretationStrategy} beans.
 */
@Component
@Configuration
public class BasicAbsenceInterpretationStrategyBeans {

    /**
     * Default constructor that does not perform any actions
     */
    public BasicAbsenceInterpretationStrategyBeans() {
    }

    /**
     * Bean for {@code ANY_EXISTS} interpretation strategy.
     *
     * @return bean for {@link AbsenceInterpretationStrategies#ANY_EXISTS}
     */
    @Bean
    @ConditionalOnMissingBean(name = "anyExistsAbsenceInterpretationStrategy")
    public AbsenceInterpretationStrategy anyExistsAbsenceInterpretationStrategy() {
        return AbsenceInterpretationStrategies.ANY_EXISTS;
    }

    /**
     * Bean for {@code ANY_ABSENT} interpretation strategy.
     *
     * @return bean for {@link AbsenceInterpretationStrategies#ANY_ABSENT}
     */
    @Bean
    @ConditionalOnMissingBean(name = "anyAbsentAbsenceInterpretationStrategy")
    public AbsenceInterpretationStrategy anyAbsentAbsenceInterpretationStrategy() {
        return AbsenceInterpretationStrategies.ANY_ABSENT;
    }
}
