package team.isaz.existence.core.rules;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.rules.NullAbsenceRule;

public class NullAbsenceRuleTest {
    private final AbsenceRule rule = new NullAbsenceRule();

    @Test
    public void applicable_whenObjectIsNull_thenReturnTrue() {
        Assertions
                .assertThat(rule.applicable(null))
                .isTrue();
    }

    @Test
    public void applicable_whenObjectIsNotNull_thenReturnFalse() {
        Assertions
                .assertThat(rule.applicable(new Object()))
                .isFalse();
    }

    @Test
    public void absent_whenObjectIsNull_thenReturnTrue() {
        Assertions.assertThat(rule.absent(null, null))
                .isTrue();
    }

    @Test
    public void absent_whenObjectIsNotNull_thenReturnFalse() {
        Assertions.assertThat(rule.absent(new Object(), null))
                .isFalse();
    }

}
