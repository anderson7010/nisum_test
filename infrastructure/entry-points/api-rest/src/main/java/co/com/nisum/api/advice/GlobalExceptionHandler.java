package co.com.nisum.api.advice;

import co.com.nisum.api.common.response.ErrorResponse;
import co.com.nisum.api.common.response.ResponseMessage;
import co.com.nisum.model.common.Constant;
import co.com.nisum.model.exception.BusinessException;
import co.com.nisum.model.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(ResponseMessage.INVALID_REQUEST_ERROR_MESSAGE.getMessage());
        ex.getBindingResult().getFieldErrors().forEach(error -> sb.append(Constant.PERIOD.getValue())
                .append(Constant.SPACE.getValue()).append(error.getField())
                .append(Constant.SPACE.getValue()).append(error.getDefaultMessage()));
        log.error(sb.toString());
        return ResponseEntity.unprocessableEntity().body(ErrorResponse.build(sb.toString()));
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        String responseMessage = ResponseMessage.INVALID_REQUEST_ERROR_MESSAGE.getMessage()
                + Constant.PERIOD.getValue() + Constant.SPACE.getValue() + ex.getValue() +
                ResponseMessage.INVALID_VALUE_ERROR_MESSAGE.getMessage();
        log.error(responseMessage);
        return ResponseEntity.badRequest().body(ErrorResponse.build(responseMessage));
    }

    @ExceptionHandler({IllegalArgumentException.class, BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ErrorResponse.build(ex.getMessage()));
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNotFoundException() {
        log.error(ResponseMessage.USER_NOT_FOUND_ERROR_MESSAGE.getMessage());
        return ResponseEntity.badRequest()
                .body(ErrorResponse.build(ResponseMessage.USER_NOT_FOUND_ERROR_MESSAGE.getMessage()));
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleAuthenticationException() {
        log.error(ResponseMessage.AUTHENTICATION_ERROR_MESSAGE.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.build(ResponseMessage.AUTHENTICATION_ERROR_MESSAGE.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException() {
        log.error(ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE.getMessage());
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.build(ResponseMessage.INTERNAL_SERVER_ERROR_MESSAGE.getMessage()));
    }
}
