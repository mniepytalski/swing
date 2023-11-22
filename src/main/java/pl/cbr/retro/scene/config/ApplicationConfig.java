package pl.cbr.retro.scene.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationConfig {
    private String title;
    private Size size;
}
