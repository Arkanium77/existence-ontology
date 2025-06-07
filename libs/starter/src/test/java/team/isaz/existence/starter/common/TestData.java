package team.isaz.existence.starter.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class TestData {

    public static Stream<Object> allDataTypesEmpty() {
        var emptiable = EmptyIfValueLessThan10.of(9);
        var string = "          ";
        var map = Map.of("key1", emptiable, "key2", string);
        var collection = new ArrayList<>();
        collection.add(emptiable);
        collection.add(string);
        collection.add(null);
        collection.add(new ArrayList<>(collection));
        collection.add(map);
        return Stream.of(
                null,
                string,
                emptiable,
                Optional.empty(),
                Optional.of(string),
                Optional.of(emptiable),
                Collections.emptyList(),
                Map.of(),
                map,
                collection
        );
    }

    public static Stream<Object> allDataTypesNotEmpty() {
        var emptiable = EmptyIfValueLessThan10.of(11);
        var emptyEmptiable = EmptyIfValueLessThan10.of(9);
        var string = "     x     ";
        var map = Map.of("key1", emptiable, "key2", string);
        var collection = new ArrayList<>();
        collection.add(emptyEmptiable);
        collection.add("");
        collection.add(null);
        collection.add(List.of(emptiable, string));
        return Stream.of(
                string,
                emptiable,
                Optional.of(string),
                Optional.of(emptiable),
                map,
                collection
        );
    }
}
