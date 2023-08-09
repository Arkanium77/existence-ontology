package team.isaz.existence.core.rules;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.rules.OptionalAbsenceRule;

import java.util.Optional;

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
    public void absent_whenOptionalIsPresent_thenReturnFalse() {
        Optional<Integer> o = Optional.of(1);
        Assertions
                .assertThat(rule.absent(o, null))
                .isFalse();
    }

    @Test
    public void absent_whenOptionalIsNotPresent_thenReturnTrue() {
        Optional<Integer> o = Optional.empty();
        Assertions
                .assertThat(rule.absent(o, null))
                .isTrue();
    }

}
