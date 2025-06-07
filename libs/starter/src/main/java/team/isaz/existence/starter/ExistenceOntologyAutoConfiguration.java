package team.isaz.existence.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.isaz.existence.core.BasicExistenceChecker;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

@Configuration
public class ExistenceOntologyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ExistenceChecker.class)
    public ExistenceChecker existenceChecker() {
        return new BasicExistenceChecker();
    }
}
