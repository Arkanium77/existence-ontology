package team.isaz.existence.starter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

import static org.assertj.core.api.Assertions.assertThat;

class ExistenceOntologyAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(ExistenceOntologyAutoConfiguration.class);

    @Test
    void autoConfigurationProvidesExistenceCheckerBean() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(ExistenceChecker.class);
        });
    }
}
