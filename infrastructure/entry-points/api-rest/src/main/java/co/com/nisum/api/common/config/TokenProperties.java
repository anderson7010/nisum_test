package co.com.nisum.api.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.jwt")
@Data
public class TokenProperties {
    String secret;
    String validationTime;
}
