package team.isaz.existence.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.isaz.existence.core.model.interfaces.InlineAbsenceRule;

@Configuration
public class MyConfiguration {
    @Bean
    public InlineAbsenceRule anyIsAbsent() {
        return (o, c) -> true;
    }

    @Bean
    public InlineAbsenceRule anyIsExists() {
        return (o, c) -> false;
    }
}