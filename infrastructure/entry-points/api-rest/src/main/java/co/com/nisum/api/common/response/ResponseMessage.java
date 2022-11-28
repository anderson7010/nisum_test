package co.com.nisum.api.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {
    INVALID_REQUEST_ERROR_MESSAGE("There was a problem with the request"),
    INVALID_VALUE_ERROR_MESSAGE(" is an invalid value."),
    USER_NOT_FOUND_ERROR_MESSAGE("User not found."),
    AUTHENTICATION_ERROR_MESSAGE("Authentication failed. Bad credentials."),
    EMAIL_VALIDATION_ERROR_MESSAGE("Invalid format email."),
    PASSWORD_VALIDATION_ERROR_MESSAGE("Invalid format password."),
    INTERNAL_SERVER_ERROR_MESSAGE("There was an unexpected error. Please contact system admin."),
    LOGIN_SUCCESS_MESSAGE("Login was successful."),
    USER_CREATION_SUCCESS_MESSAGE("User created successfully."),
    USER_QUERY_SUCCESS_MESSAGE("Users consultation successful."),
    USER_DELETION_SUCCESS_MESSAGE("Users deletion successful.");

    private final String message;
}
