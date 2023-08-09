package team.isaz.existence.core.model.dto;

import java.util.Map;

public class CheckResult {
    private final long absentResults;
    private final long existsResults;

    public CheckResult(Map<Boolean, Long> results) {
        this.absentResults = results.getOrDefault(true, 0L);
        this.existsResults = results.getOrDefault(false, 0L);
    }

    public long absentResults() {
        return absentResults;
    }

    public long existsResults() {
        return existsResults;
    }

    public boolean noResults() {
        return absentResults == 0 && existsResults == 0;
    }
}
