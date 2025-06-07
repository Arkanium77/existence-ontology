package team.isaz.existence.starter.components.checker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;
import team.isaz.existence.starter.common.TestData;
import team.isaz.existence.starter.config.ExistenceOntologyAutoConfiguration;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@ActiveProfiles("alternative-rules")
@SpringBootTest(classes = ExistenceOntologyAutoConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AlternativeStringRuleTest {
    @Autowired
    private ExistenceChecker existenceChecker;


    @MethodSource
    @ParameterizedTest
    void existenceChecker_absent(Object o) {
        // Cases involving strings have changed behavior
        boolean changedBehaviour = o instanceof String
                || o instanceof Optional<?> op && op.isPresent() && op.get() instanceof String
                || o instanceof Map<?, ?> m && m.values().stream().anyMatch(v -> v instanceof String)
                || o instanceof Collection<?> c && c.stream().anyMatch(v -> v instanceof String);
        if (changedBehaviour) {
            Assertions.assertThat(existenceChecker.absent(o))
                    .isFalse();
            Assertions.assertThat(existenceChecker.absent(""))
                    .isTrue();
            return;
        }
        // The rest of the cases work as before
        Assertions.assertThat(existenceChecker.absent(o))
                .isTrue();
    }

    static Stream<Arguments> existenceChecker_absent() {
        return TestData.allDataTypesEmpty().map(Arguments::of);
    }
}
