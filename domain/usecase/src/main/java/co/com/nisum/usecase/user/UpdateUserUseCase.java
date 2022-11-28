package co.com.nisum.usecase.user;

import co.com.nisum.model.exception.UserNotFoundException;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public void updateUserLoginInfo(String email, String token) throws UserNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (Objects.nonNull(user)) {
            user.setLastLogin(LocalDateTime.now());
            user.setModified(LocalDateTime.now());
            user.setToken(token);
            userRepository.saveUser(user);
        } else {
            throw new UserNotFoundException();
        }
    }
}
