package co.com.nisum.config.jpa;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DBProperty {
    private final String url;
    private final String username;
    private final String password;
}
