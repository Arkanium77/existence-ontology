package team.isaz.existence.starter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;
import team.isaz.existence.starter.components.BeanBasedExistenceChecker;
import team.isaz.existence.starter.config.ExistenceOntologyAutoConfiguration;
import team.isaz.existence.starter.config.StaticBeans;

@SpringBootTest(classes = ExistenceOntologyAutoConfiguration.class)
@DisplayName("Auto configuration loads")
class ExistenceOntologyAutoConfigurationTest {
    @Autowired
    private ExistenceChecker existenceChecker;

    @Test
    void contextLoads() {
        Assertions.assertThat(existenceChecker)
                .isNotNull()
                .isInstanceOf(BeanBasedExistenceChecker.class);
        Assertions.assertThat(existenceChecker)
                .isEqualTo(StaticBeans.existenceChecker());
    }
}
