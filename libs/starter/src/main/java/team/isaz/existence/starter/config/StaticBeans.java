package team.isaz.existence.starter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Provides access to beans after initialization.
 */
@Component
@RequiredArgsConstructor
public class StaticBeans implements InitializingBean {

    private final ExistenceChecker existenceChecker;

    private static StaticBeans instance;

    /**
     * Initialize the static holder after bean construction.
     */
    @Override
    public void afterPropertiesSet() {
        instance = this;
    }

    /**
     * @return the configured {@link ExistenceChecker} instance
     */
    public static ExistenceChecker existenceChecker() {
        if (instance == null) {
            throw new IllegalStateException("StaticBeans not initialized yet");
        }
        return instance.existenceChecker;
    }
}
