package co.com.nisum.api.advice;

import co.com.nisum.api.common.response.ErrorResponse;
import co.com.nisum.model.exception.BusinessException;
import co.com.nisum.model.exception.UserNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("There was a problem with the request");
        ex.getBindingResult().getFieldErrors().forEach(error -> sb.append(", ").append(error.getField())
                .append(" ").append(error.getDefaultMessage()));
        ErrorResponse response = ErrorResponse.build(sb.toString());
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        String sb = "There was a problem with the request. " + ex.getValue() + " is an invalid value.";
        ErrorResponse response = ErrorResponse.build(sb);
        return ResponseEntity.unprocessableEntity().body(response);
    }

    @ExceptionHandler({IllegalArgumentException.class, BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.build(ex.getMessage()));
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.build("User not found."));
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.build("Authentication failed. Bad credentials."));
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.build("There was an unexpected error. Please contact system admin."));
    }
}
