package co.com.nisum.api.auth;

import co.com.nisum.api.auth.request.AuthRequest;
import co.com.nisum.api.common.response.SuccessResponse;
import co.com.nisum.model.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(value = "/api/v1/auth", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication controller",
        description = "Controller to login and get the token to access to the user resources")
public interface AuthApi {
    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Login user", description = "Process of user authentication")
    ResponseEntity<SuccessResponse<String>> authenticateUser(
            @RequestBody AuthRequest authRequest) throws UserNotFoundException;
}
