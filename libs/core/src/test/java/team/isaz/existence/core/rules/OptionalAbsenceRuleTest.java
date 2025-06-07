package team.isaz.existence.core.rules;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;
import team.isaz.existence.core.model.rules.OptionalAbsenceRule;

import java.util.Optional;
@DisplayName("Optional absence rule")

public class OptionalAbsenceRuleTest {
    private final AbsenceRule rule = new OptionalAbsenceRule();

    @Test
    public void applicable_whenObjectIsInstanceOfOptional_thenReturnTrue() {
        Assertions
                .assertThat(rule.applicable(Optional.empty()))
                .isTrue();
    }

    @Test
    public void applicable_whenObjectIsNotInstanceOfOptional_thenReturnFalse() {
        Assertions
                .assertThat(rule.applicable("The String is not Optional"))
                .isFalse();
    }

    @Test
    public void absent_whenOptionalIsPresentAndItsValueIsExists_thenReturnFalse() {
        ExistenceChecker mock = Mockito.mock(ExistenceChecker.class);
        Mockito.when(mock.absent(ArgumentMatchers.any())).thenReturn(false);
        Optional<Integer> o = Optional.of(1);
        Assertions
                .assertThat(rule.absent(o, mock))
                .isFalse();
    }

    @Test
    public void absent_whenOptionalIsPresentAndItsValueIsAbsent_thenReturnTrue() {
        ExistenceChecker mock = Mockito.mock(ExistenceChecker.class);
        Mockito.when(mock.absent(ArgumentMatchers.any())).thenReturn(true);
        Optional<Integer> o = Optional.of(1);
        Assertions
                .assertThat(rule.absent(o, mock))
                .isTrue();
    }

    @Test
    public void absent_whenOptionalIsNotPresent_thenReturnTrue() {
        Optional<Integer> o = Optional.empty();
        Assertions
                .assertThat(rule.absent(o, null))
                .isTrue();
    }

}
