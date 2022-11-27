package co.com.nisum.api.auth;

import co.com.nisum.api.auth.config.UserDetailsImpl;
import co.com.nisum.api.auth.request.AuthRequest;
import co.com.nisum.api.auth.util.TokenUtils;
import co.com.nisum.api.response.Response;
import co.com.nisum.usecase.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<Response<String>> authenticateUser(@RequestBody AuthRequest authRequest) {
        Response<String> response = new Response<>();
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUsername());
        updateUserUseCase.updateUserLoginInfo(userDetails.getUsername(), token);
        response.setData("OK");
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).body(response);
    }
}
