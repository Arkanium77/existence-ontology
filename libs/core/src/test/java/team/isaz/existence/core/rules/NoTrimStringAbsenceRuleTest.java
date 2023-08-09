package team.isaz.existence.core.rules;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.rules.NoTrimStringAbsenceRule;

public class NoTrimStringAbsenceRuleTest {
    private final AbsenceRule rule = new NoTrimStringAbsenceRule();

    @Test
    public void applicable_whenObjectIsInstanceOfString_thenReturnTrue() {
        Assertions
                .assertThat(rule.applicable("The String is String"))
                .isTrue();
    }

    @Test
    public void applicable_whenObjectIsNotInstanceOfString_thenReturnFalse() {
        Assertions
                .assertThat(rule.applicable(42))
                .isFalse();
    }

    @Test
    public void absent_whenStringHoldsText_thenReturnFalse() {
        String o = "The String is String";
        Assertions
                .assertThat(rule.absent(o, null))
                .isFalse();
    }

    @Test
    public void absent_whenStringHoldsWhitespacesAndNonPrintableChars_thenReturnFalse() {
        String o = " \t\r\n   \n ";
        Assertions
                .assertThat(rule.absent(o, null))
                .isFalse();
    }

    @Test
    public void absent_whenStringIsEmpty_thenReturnTrue() {
        String o = "";
        Assertions
                .assertThat(rule.absent(o, null))
                .isTrue();
    }

}
