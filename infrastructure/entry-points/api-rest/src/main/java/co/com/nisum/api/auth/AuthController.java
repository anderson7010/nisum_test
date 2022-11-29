package co.com.nisum.api.auth;

import co.com.nisum.api.auth.config.UserDetailsImpl;
import co.com.nisum.api.auth.request.AuthRequest;
import co.com.nisum.api.common.response.ResponseMessage;
import co.com.nisum.api.common.util.TokenUtils;
import co.com.nisum.api.common.response.SuccessResponse;
import co.com.nisum.model.common.Constant;
import co.com.nisum.model.exception.UserNotFoundException;
import co.com.nisum.usecase.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController implements AuthApi {
    private final AuthenticationManager authManager;
    private final UpdateUserUseCase updateUserUseCase;
    private final TokenUtils tokenUtils;

    public ResponseEntity<SuccessResponse<String>> authenticateUser(
            AuthRequest authRequest) throws UserNotFoundException {
        SuccessResponse<String> response = new SuccessResponse<>();
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = tokenUtils.createToken(userDetails.getUsername());
        updateUserUseCase.updateUserLoginInfo(userDetails.getUsername(), token);
        token = Constant.TOKEN_PREFIX.getValue() + token;
        response.setData(token);
        response.setMessage(ResponseMessage.LOGIN_SUCCESS_MESSAGE.getMessage());

        log.info(ResponseMessage.LOGIN_SUCCESS_MESSAGE.getMessage());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(response);
    }
}
