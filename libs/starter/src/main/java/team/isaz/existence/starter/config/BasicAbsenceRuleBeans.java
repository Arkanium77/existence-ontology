package team.isaz.existence.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.rules.CollectionAbsenceRule;
import team.isaz.existence.core.model.rules.EmptiableAbsenceRule;
import team.isaz.existence.core.model.rules.MapAbsenceRule;
import team.isaz.existence.core.model.rules.NoTrimStringAbsenceRule;
import team.isaz.existence.core.model.rules.NullAbsenceRule;
import team.isaz.existence.core.model.rules.OptionalAbsenceRule;
import team.isaz.existence.core.model.rules.StringAbsenceRule;

@Component
@Configuration
/**
 * Registers default {@link AbsenceRule} beans.
 */
public class BasicAbsenceRuleBeans {

    @Bean
    @ConditionalOnMissingBean(name = "collectionAbsenceRule")
    public AbsenceRule collectionAbsenceRule() {
        return new CollectionAbsenceRule();
    }

    @Bean
    @ConditionalOnMissingBean(name = "emptiableAbsenceRule")
    public AbsenceRule emptiableAbsenceRule() {
        return new EmptiableAbsenceRule();
    }

    @Bean
    @ConditionalOnMissingBean(name = "mapAbsenceRule")
    public AbsenceRule mapAbsenceRule() {
        return new MapAbsenceRule();
    }

    @Bean
    @ConditionalOnMissingBean(name = "noTrimStringAbsenceRule")
    public AbsenceRule noTrimStringAbsenceRule() {
        return new NoTrimStringAbsenceRule();
    }

    @Bean
    @ConditionalOnMissingBean(name = "nullAbsenceRule")
    public AbsenceRule nullAbsenceRule() {
        return new NullAbsenceRule();
    }

    @Bean
    @ConditionalOnMissingBean(name = "optionalAbsenceRule")
    public AbsenceRule optionalAbsenceRule() {
        return new OptionalAbsenceRule();
    }

    @Bean
    @ConditionalOnMissingBean(name = "stringAbsenceRule")
    public AbsenceRule stringAbsenceRule() {
        return new StringAbsenceRule();
    }
}
