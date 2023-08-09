package team.isaz.existence.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import team.isaz.existence.core.model.constants.AbsenceInterpretationStrategies;
import team.isaz.existence.core.model.dto.CheckResult;
import team.isaz.existence.core.model.interfaces.AbsenceInterpretationStrategy;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

import java.util.Arrays;
import java.util.Collections;

public class BasicExistenceCheckerTest {
    private final MockAbsenceRule alwaysTrue = (a, b) -> true;
    private final MockAbsenceRule alwaysFalse = (a, b) -> false;
    private final MockAbsenceRule evenIsAbsent = (a, b) -> ((int) a) % 2 == 0;
    private final AbsenceInterpretationStrategy throwAnyStrategy = r -> {
        throw new ResultHoldingException(r);
    };


    @Test
    public void absent_whenNoRulesAndStrategyAnyExists_thenReturnTrue() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS);

        boolean result = checker.absent(new Object());
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void absent_whenNoRulesAndStrategyAnyAbsent_thenReturnTrue() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_ABSENT);

        boolean result = checker.absent(0);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void absent_whenNonConsistentRulesAndStrategyAnyExists_thenReturnFalse() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(alwaysTrue)
                .register(alwaysFalse);

        boolean result = checker.absent(0);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void absent_whenNonConsistentRulesAndStrategyAnyAbsent_thenReturnTrue() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_ABSENT)
                .register(alwaysTrue)
                .register(alwaysFalse);

        boolean result = checker.absent(0);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void absent_when3RulesRegistered_then3ResultsReceivedByStrategy() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(throwAnyStrategy)
                .registerAll(Arrays.asList(alwaysTrue, alwaysFalse, evenIsAbsent));
        Boolean value = null;
        try {
            value = checker.absent(0);
        } catch (ResultHoldingException e) {
            CheckResult result = e.getResult();
            Assertions
                    .assertThat(result.absentResults())
                    .isEqualTo(2);
            Assertions
                    .assertThat(result.existsResults())
                    .isEqualTo(1);
        }

        Assertions.assertThat(value).isNull();
    }

    @Test
    public void absent_whenEvenIsAbsentRule_then0IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.absent(0)).isTrue();
    }

    @Test
    public void absent_whenEvenIsAbsentRule_then1IsExists() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.absent(1)).isFalse();
    }

    @Test
    public void exists_whenEvenIsAbsentRule_then0IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.exists(0)).isFalse();
    }

    @Test
    public void exists_whenEvenIsAbsentRule_then1IsExists() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.exists(1)).isTrue();
    }

    @Test
    public void anyAbsent_whenEvenIsAbsentRule_then0And1IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.anyAbsent(0, 1)).isTrue();
    }

    @Test
    public void anyAbsent_whenEvenIsAbsentRule_then0And2IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.anyAbsent(0, 2)).isTrue();
    }

    @Test
    public void anyAbsent_whenEvenIsAbsentRule_then1And3IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.anyAbsent(1, 3)).isFalse();
    }

    @Test
    public void allAbsent_whenEvenIsAbsentRule_then0And1IsExists() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.allAbsent(0, 1)).isFalse();
    }

    @Test
    public void allAbsent_whenEvenIsAbsentRule_then0And2IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.allAbsent(0, 2)).isTrue();
    }

    @Test
    public void allAbsent_whenEvenIsAbsentRule_then1And3IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.allAbsent(1, 3)).isFalse();
    }

    @Test
    public void anyExists_whenEvenIsAbsentRule_then0And1IsExists() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.anyExists(0, 1)).isTrue();
    }

    @Test
    public void anyExists_whenEvenIsAbsentRule_then0And2IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.anyExists(0, 2)).isFalse();
    }

    @Test
    public void anyExists_whenEvenIsAbsentRule_then1And3IsExists() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.anyExists(1, 3)).isTrue();
    }

    @Test
    public void allExists_whenEvenIsAbsentRule_then0And1IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.allExists(0, 1)).isFalse();
    }

    @Test
    public void allExists_whenEvenIsAbsentRule_then0And2IsAbsent() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.allExists(0, 2)).isFalse();
    }

    @Test
    public void allExists_whenEvenIsAbsentRule_then1And3IsExists() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(AbsenceInterpretationStrategies.ANY_EXISTS)
                .register(evenIsAbsent);
        Assertions.assertThat(checker.allExists(1, 3)).isTrue();
    }

    @Test
    public void setup_whenSetupAlwaysTrueRule_thenResultOfAbsentIsTrue() {
        ExistenceChecker checker = new BasicExistenceChecker(Collections.singleton(alwaysFalse));
        Assertions.assertThat(checker.absent(0)).isFalse();
        checker = checker.setup(Collections.singleton(alwaysTrue));
        Assertions.assertThat(checker.absent(0)).isTrue();
    }

    @Test
    public void copy_whenCopyExistenceChecker_thenCheckerNotEqualsButResultsAreTheSame() {
        ExistenceChecker checker = new BasicExistenceChecker()
                .strategy(throwAnyStrategy)
                .registerAll(Arrays.asList(alwaysTrue, alwaysFalse, evenIsAbsent));
        ExistenceChecker anotherChecker = checker.copy();
        CheckResult result = extractCheckResult(() -> checker.absent(0));
        CheckResult anotherResult = extractCheckResult(() -> anotherChecker.absent(0));

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(anotherResult).isNotNull();

        Assertions.assertThat(checker).isNotEqualTo(anotherChecker);

        Assertions
                .assertThat(result.absentResults())
                .isEqualTo(anotherResult.absentResults())
                .isEqualTo(2);
        Assertions
                .assertThat(result.existsResults())
                .isEqualTo(anotherResult.existsResults())
                .isEqualTo(1);
    }

    private static CheckResult extractCheckResult(Runnable action) {
        CheckResult result = null;
        try {
            action.run();
        } catch (ResultHoldingException e) {
            result = e.getResult();
        }
        return result;
    }

    @FunctionalInterface
    public interface MockAbsenceRule extends AbsenceRule {
        @Override
        default boolean applicable(Object o) {
            return true;
        }
    }

    private static class ResultHoldingException extends RuntimeException {
        private final CheckResult result;

        public ResultHoldingException(CheckResult result) {
            super();
            this.result = result;
        }

        public CheckResult getResult() {
            return result;
        }
    }

}
