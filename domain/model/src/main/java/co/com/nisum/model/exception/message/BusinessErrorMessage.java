package co.com.nisum.model.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorMessage {
    USER_ALREADY_EXISTS("Email already registered.");

    private final String message;
}
