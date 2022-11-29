package co.com.nisum.api.user;

import co.com.nisum.api.common.response.SuccessResponse;
import co.com.nisum.api.user.request.UserRequest;
import co.com.nisum.api.user.response.UserResponse;
import co.com.nisum.model.exception.BusinessException;
import co.com.nisum.model.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User controller",
        description = "Controller to create, delete and get users")
public interface UserApi {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Create user", description = "Process of user creation")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(
            @Valid @RequestBody UserRequest userRequest) throws BusinessException;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Get all users", description = "Method to get all users")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SuccessResponse<List<UserResponse>>> getUsers();

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user", description = "Process of user deletion")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) throws UserNotFoundException;
}
