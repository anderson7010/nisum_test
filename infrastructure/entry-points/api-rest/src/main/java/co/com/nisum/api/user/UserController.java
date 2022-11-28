package co.com.nisum.api.user;

import co.com.nisum.api.common.config.RegexProperties;
import co.com.nisum.api.common.util.TokenUtils;
import co.com.nisum.api.common.response.SuccessResponse;
import co.com.nisum.api.user.request.UserRequest;
import co.com.nisum.api.user.response.UserResponse;
import co.com.nisum.model.exception.BusinessException;
import co.com.nisum.model.exception.UserNotFoundException;
import co.com.nisum.model.exception.message.BusinessErrorMessage;
import co.com.nisum.model.validation.ValidationResult;
import co.com.nisum.usecase.user.CreateUserUseCase;
import co.com.nisum.usecase.user.DeleteUserUseCase;
import co.com.nisum.usecase.user.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final PasswordEncoder passwordEncoder;
    private final RegexProperties regexProperties;

    @PostMapping
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(
            @Valid @RequestBody UserRequest userRequest) throws Exception {
        SuccessResponse<UserResponse> response = new SuccessResponse<>();
        ValidationResult validationResult = userRequest.isValid(regexProperties);
        if (!validationResult.isValid()) {
            throw new IllegalArgumentException(validationResult.getMessage());
        }
        if (getUserUseCase.existsUserByEmail(userRequest.getEmail())) {
            throw new BusinessException(BusinessErrorMessage.USER_ALREADY_EXISTS);
        }
        String token = TokenUtils.createToken(userRequest.getEmail());
        UserResponse userResponse = new UserResponse(createUserUseCase
                .createUser(userRequest.toModel(passwordEncoder), token));
        response.setData(userResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<UserResponse>>> getUsers() {
        SuccessResponse<List<UserResponse>> response = new SuccessResponse<>();
        List<UserResponse> users = getUserUseCase.getUsers().stream()
                .map(UserResponse::new).collect(Collectors.toList());
        response.setData(users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) throws UserNotFoundException {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
