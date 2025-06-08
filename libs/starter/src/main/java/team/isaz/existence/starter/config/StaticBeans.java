package team.isaz.existence.starter.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.isaz.existence.core.model.interfaces.ExistenceChecker;

/**
 * Provides access to beans after initialization.
 */
@Component
public class StaticBeans implements InitializingBean {
    private static StaticBeans instance;
    private final ExistenceChecker existenceChecker;

    /**
     * Autowire constructor
     * @param existenceChecker bean that needs static access
     */
    @Autowired
    public StaticBeans(ExistenceChecker existenceChecker) {
        this.existenceChecker = existenceChecker;
    }

    /**
     * Using the default constructor is not allowed
     */
    private StaticBeans() {
        throw new AssertionError("Non-instantiable constructor");
    }

    /**
     * Initialize the static holder after bean construction.
     */
    @Override
    public void afterPropertiesSet() {
        instance = this;
    }

    /**
     * Static access to ExistenceChecker singleton
     *
     * @return the configured {@link ExistenceChecker} instance
     */
    public static ExistenceChecker existenceChecker() {
        if (instance == null) {
            throw new IllegalStateException("StaticBeans not initialized yet");
        }
        return instance.existenceChecker;
    }
}
