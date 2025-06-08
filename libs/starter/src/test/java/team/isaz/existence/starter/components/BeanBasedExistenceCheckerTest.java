package team.isaz.existence.starter.components;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;
import team.isaz.existence.starter.common.EmptyIfValueLessThan10;
import team.isaz.existence.starter.common.TestData;
import team.isaz.existence.starter.common.TestParent;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("Bean based checker")
public class BeanBasedExistenceCheckerTest extends TestParent {
    @Autowired
    private ExistenceChecker existenceChecker;


    @MethodSource
    @ParameterizedTest
    void existenceChecker_absent(Object o) {
        Assertions.assertThat(existenceChecker.absent(o))
                .isTrue();
    }

    static Stream<Arguments> existenceChecker_absent() {
        return TestData.allDataTypesEmpty().map(Arguments::of);
    }

    @MethodSource
    @ParameterizedTest
    void existenceChecker_exists(Object o) {
        Assertions.assertThat(existenceChecker.exists(o))
                .isTrue();
    }

    static Stream<Arguments> existenceChecker_exists() {
        return TestData.allDataTypesNotEmpty().map(Arguments::of);
    }

    @Test
    void existenceChecker_allAbsent() {
        var args = TestData.allDataTypesEmpty().toArray();
        Assertions.assertThat(existenceChecker.allAbsent(args))
                .isTrue();
    }

    @Test
    void existenceChecker_allExists() {
        var args = TestData.allDataTypesNotEmpty().toArray();
        Assertions.assertThat(existenceChecker.allExists(args))
                .isTrue();
    }

    @Test
    void existenceChecker_anyAbsent() {
        var list = TestData.allDataTypesNotEmpty().collect(Collectors.toList());
        list.add(EmptyIfValueLessThan10.of(9));
        var args = list.toArray();
        Assertions.assertThat(existenceChecker.anyAbsent(args))
                .isTrue();
    }

    @Test
    void existenceChecker_anyExists() {
        var list = TestData.allDataTypesEmpty().collect(Collectors.toList());
        list.add(EmptyIfValueLessThan10.of(11));
        var args = list.toArray();
        Assertions.assertThat(existenceChecker.anyExists(args))
                .isTrue();
    }
}
