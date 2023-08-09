package team.isaz.existence.core.rules;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import team.isaz.existence.core.model.interfaces.AbsenceRule;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;
import team.isaz.existence.core.model.rules.MapAbsenceRule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapAbsenceRuleTest {
    private final AbsenceRule rule = new MapAbsenceRule();

    @Test
    public void applicable_whenObjectIsInstanceOfMap_thenReturnTrue() {
        Assertions
                .assertThat(rule.applicable(Collections.emptyMap()))
                .isTrue();
    }

    @Test
    public void applicable_whenObjectIsNotInstanceOfMap_thenReturnFalse() {
        Assertions
                .assertThat(rule.applicable("The String is not Map"))
                .isFalse();
    }

    @Test
    public void absent_whenMapIsNotEmpty_thenReturnFalse() {
        ExistenceChecker mock = Mockito.mock(ExistenceChecker.class);
        Mockito.when(mock.absent(ArgumentMatchers.any())).thenReturn(false);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        Assertions
                .assertThat(rule.absent(map, mock))
                .isFalse();
    }

    @Test
    public void absent_whenMapIsNotEmptyButAllValuesIsEmpty_thenReturnTrue() {
        ExistenceChecker mock = Mockito.mock(ExistenceChecker.class);
        Mockito.when(mock.absent(ArgumentMatchers.any())).thenReturn(true);

        Map<Integer, Object> map = new HashMap<>();
        map.put(1, 0);
        map.put(2, null);
        map.put(3, "");
        Assertions
                .assertThat(rule.absent(map, mock))
                .isTrue();
    }

    @Test
    public void absent_whenCollectionIsEmpty_thenReturnTrue() {
        Assertions
                .assertThat(rule.absent(Collections.emptyMap(), null))
                .isTrue();
    }

}
