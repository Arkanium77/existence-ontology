package team.isaz.existence.starter.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import team.isaz.existence.core.model.interfaces.Emptiable;

@Data
@AllArgsConstructor(staticName = "of")
public class EmptyIfValueLessThan10 implements Emptiable {
    private int value;

    @Override
    public boolean empty() {
        return value < 10;
    }
}
