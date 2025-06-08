package team.isaz.existence.core.rules;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;
import team.isaz.existence.core.model.rules.CollectionAbsenceRule;

import java.util.Arrays;
import java.util.Collections;
@DisplayName("Collection absence rule")

public class CollectionAbsenceRuleTest {
    private final AbsenceRule rule = new CollectionAbsenceRule();

    @Test
    public void applicable_whenObjectIsInstanceOfCollection_thenReturnTrue() {
        Assertions
                .assertThat(rule.applicable(Collections.emptySet()))
                .isTrue();
    }

    @Test
    public void applicable_whenObjectIsNotInstanceOfCollection_thenReturnFalse() {
        Assertions
                .assertThat(rule.applicable("The String is not Collection"))
                .isFalse();
    }

    @Test
    public void absent_whenCollectionIsNotEmpty_thenReturnFalse() {
        ExistenceChecker mock = Mockito.mock(ExistenceChecker.class);
        Mockito.when(mock.absent(ArgumentMatchers.any())).thenReturn(false);

        Assertions
                .assertThat(rule.absent(Arrays.asList(1, 2, 3, 4), mock))
                .isFalse();
    }

    @Test
    public void absent_whenCollectionIsNotEmptyButEveryElementIsEmpty_thenReturnTrue() {
        ExistenceChecker mock = Mockito.mock(ExistenceChecker.class);
        Mockito.when(mock.absent(ArgumentMatchers.any())).thenReturn(true);

        Assertions
                .assertThat(rule.absent(Arrays.asList(0, null, ""), mock))
                .isTrue();
    }

    @Test
    public void absent_whenCollectionIsEmpty_thenReturnTrue() {
        Assertions
                .assertThat(rule.absent(Collections.emptySet(), null))
                .isTrue();
    }

}
