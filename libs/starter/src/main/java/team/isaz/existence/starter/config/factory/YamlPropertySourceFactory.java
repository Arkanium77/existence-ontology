package team.isaz.existence.starter.config.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Loads YAML files as Spring property sources.
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public @NonNull PropertySource<?> createPropertySource(@Nullable String name, EncodedResource encodedResource) {
        var factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());

        var properties = Objects.requireNonNull(factory.getObject());

        var resource = Objects.requireNonNull(encodedResource.getResource());
        var fileName = Objects.requireNonNull(resource.getFilename());


        return new PropertiesPropertySource(fileName, properties);
    }
}
