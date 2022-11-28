package co.com.nisum.model.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Constant {
    AUTHORIZATION_HEADER("Authorization"),
    TOKEN_PREFIX("Bearer "),
    EMPTY_STRING(""),
    PERIOD("."),
    SPACE(" ");

    private final String value;
}
