package team.isaz.existence.starter.utils;

import lombok.experimental.ExtensionMethod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import team.isaz.existence.starter.common.EmptyIfValueLessThan10;
import team.isaz.existence.starter.common.TestData;
import team.isaz.existence.starter.common.TestParent;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtensionMethod(ExistenceUtils.class)
@DisplayName("ExistenceUtils extension methods")
public class ExistenceUtilsExtensionTest extends TestParent {

    @MethodSource
    @ParameterizedTest
    void existenceChecker_absent(Object o) {
        Assertions.assertThat(o.absent())
                .isTrue();
    }

    static Stream<Arguments> existenceChecker_absent() {
        return TestData.allDataTypesEmpty().map(Arguments::of);
    }

    @MethodSource
    @ParameterizedTest
    void existenceChecker_exists(Object o) {
        Assertions.assertThat(o.exists())
                .isTrue();
    }

    static Stream<Arguments> existenceChecker_exists() {
        return TestData.allDataTypesNotEmpty().map(Arguments::of);
    }

    @Test
    void existenceChecker_allAbsent() {
        var args = TestData.allDataTypesEmpty().toArray();
        Assertions.assertThat(args.allAbsent())
                .isTrue();
    }

    @Test
    void existenceChecker_allExists() {
        var args = TestData.allDataTypesNotEmpty().toArray();
        Assertions.assertThat(args.allExists())
                .isTrue();
    }

    @Test
    void existenceChecker_anyAbsent() {
        var list = TestData.allDataTypesNotEmpty().collect(Collectors.toList());
        list.add(EmptyIfValueLessThan10.of(9));
        Assertions.assertThat(list.toArray().anyAbsent())
                .isTrue();
    }

    @Test
    void existenceChecker_anyExists() {
        var list = TestData.allDataTypesEmpty().collect(Collectors.toList());
        list.add(EmptyIfValueLessThan10.of(11));
        Assertions.assertThat(list.toArray().anyExists())
                .isTrue();
    }
}
