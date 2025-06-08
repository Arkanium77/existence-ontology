package team.isaz.existence.starter.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@ConfigurationProperties("existence-ontology")
/**
 * Configuration properties for Existence Ontology starter.
 */
public class ExistenceOntologyProperties {
    @NotBlank
    private String strategy;
    @NotNull
    private List<String> rules;
}
