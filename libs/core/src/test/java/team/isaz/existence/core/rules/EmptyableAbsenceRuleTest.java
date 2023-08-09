package team.isaz.existence.core.rules;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.Emptyable;
import team.isaz.existence.core.model.rules.EmptyableAbsenceRule;

public class EmptyableAbsenceRuleTest {
    private final AbsenceRule rule = new EmptyableAbsenceRule();

    @Test
    public void applicable_whenObjectIsInstanceOfEmptyable_thenReturnTrue() {
        Assertions
                .assertThat(rule.applicable(new TwoIntegerHolder()))
                .isTrue();
    }

    @Test
    public void applicable_whenObjectIsNotInstanceOfEmptyable_thenReturnFalse() {
        Assertions
                .assertThat(rule.applicable("Strings don't implement the Emptyable interface"))
                .isFalse();
    }

    @Test
    public void absent_whenTwoIntegerHolderHasTwoFilledFields_thenReturnFalse() {
        TwoIntegerHolder o = new TwoIntegerHolder(1, 2);
        Assertions
                .assertThat(rule.absent(o, null))
                .isFalse();
    }

    @Test
    public void absent_whenTwoIntegerHolderHasOneOrMoreEmptyFields_thenReturnTrue() {
        TwoIntegerHolder o = new TwoIntegerHolder(null, null);
        TwoIntegerHolder o1 = new TwoIntegerHolder(1, null);
        TwoIntegerHolder o2 = new TwoIntegerHolder(null, 2);

        Assertions
                .assertThat(rule.absent(o, null))
                .isEqualTo(rule.absent(o1, null))
                .isEqualTo(rule.absent(o2, null))
                .isTrue();
    }

    private static class TwoIntegerHolder implements Emptyable {
        private final Integer a;
        private final Integer b;

        public TwoIntegerHolder() {
            this.a = null;
            this.b = null;
        }

        public TwoIntegerHolder(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean empty() {
            return a == null || b == null;
        }
    }
}
