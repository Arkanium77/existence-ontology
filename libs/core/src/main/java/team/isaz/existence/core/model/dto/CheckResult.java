package team.isaz.existence.core.model.dto;

import java.util.Map;

/**
 * Holds the amount of positive and negative rule results.
 */
public class CheckResult {
    private final long absentResults;
    private final long existsResults;

    /**
     * Constructs a new instance from a map produced by
     * {@link java.util.stream.Collectors#groupingBy}.
     *
     * @param results map with a boolean key representing absence
     *                and the number of rule results for that state
     */
    public CheckResult(Map<Boolean, Long> results) {
        this.absentResults = results.getOrDefault(true, 0L);
        this.existsResults = results.getOrDefault(false, 0L);
    }

    /**
     * @return number of rules that reported absence
     */
    public long absentResults() {
        return absentResults;
    }

    /**
     * @return number of rules that reported existence
     */
    public long existsResults() {
        return existsResults;
    }

    /**
     * @return {@code true} if no rules have produced results
     */
    public boolean noResults() {
        return absentResults == 0 && existsResults == 0;
    }
}
