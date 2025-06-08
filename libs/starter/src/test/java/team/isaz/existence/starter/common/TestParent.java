package team.isaz.existence.starter.common;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import team.isaz.existence.starter.config.ExistenceOntologyAutoConfiguration;

@SpringBootTest(classes = ExistenceOntologyAutoConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public abstract class TestParent {
}
