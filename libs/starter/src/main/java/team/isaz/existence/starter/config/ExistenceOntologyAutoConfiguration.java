package team.isaz.existence.starter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import team.isaz.existence.starter.components.BeanBasedExistenceChecker;
import team.isaz.existence.starter.config.factory.YamlPropertySourceFactory;
import team.isaz.existence.starter.config.validator.ExistenceOntologyPropertiesValidator;
import team.isaz.existence.starter.properties.ExistenceOntologyProperties;

/**
 * Spring Boot autoconfiguration for Existence Ontology.
 */
@Configuration
@PropertySource(
        value = "classpath:config/existence-ontology-properties-default.yml",
        factory = YamlPropertySourceFactory.class
)
@Import(
        {
                BasicAbsenceRuleBeans.class,
                BasicAbsenceInterpretationStrategyBeans.class,
                ExistenceOntologyPropertiesValidator.class,
                BeanBasedExistenceChecker.class,
                StaticBeans.class,
        }
)
@EnableConfigurationProperties(ExistenceOntologyProperties.class)
public class ExistenceOntologyAutoConfiguration {

    /**
     * Default constructor that does not perform any actions
     */
    public ExistenceOntologyAutoConfiguration() {
    }
}
