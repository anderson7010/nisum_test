package co.com.nisum.api.user;

import co.com.nisum.api.response.Response;
import co.com.nisum.api.user.request.UserRequest;
import co.com.nisum.api.user.response.UserResponse;
import co.com.nisum.usecase.user.CreateUserUseCase;
import co.com.nisum.usecase.user.DeleteUserUseCase;
import co.com.nisum.usecase.user.GetUserUseCase;
import co.com.nisum.usecase.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

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

    @PostMapping
    public ResponseEntity<Response<UserResponse>> createUser(@RequestBody UserRequest userRequest) {
        Response<UserResponse> response = new Response<>();
        try {
            UserResponse userResponse = new UserResponse(createUserUseCase
                    .createUser(userRequest.toModel(passwordEncoder)));
            response.setData(userResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Response<List<UserResponse>>> getUsers() {
        Response<List<UserResponse>> response = new Response<>();
        try {
            List<UserResponse> users = getUserUseCase.getUsers().stream()
                    .map(UserResponse::new).collect(Collectors.toList());
            response.setData(users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteUser(@PathVariable UUID id) {
        Response<String> response = new Response<>();
        try {
            deleteUserUseCase.deleteUser(id);
            response.setData("DELETED");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
