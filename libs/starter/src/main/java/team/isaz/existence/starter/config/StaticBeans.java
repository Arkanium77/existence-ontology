package team.isaz.existence.starter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

@Component
@RequiredArgsConstructor
/**
 * Provides access to beans after initialization.
 */
public class StaticBeans implements InitializingBean {

    private final ExistenceChecker existenceChecker;

    private static StaticBeans instance;

    @Override
    public void afterPropertiesSet() {
        instance = this;
    }

    public static ExistenceChecker existenceChecker() {
        if (instance == null) {
            throw new IllegalStateException("StaticBeans not initialized yet");
        }
        return instance.existenceChecker;
    }
}
