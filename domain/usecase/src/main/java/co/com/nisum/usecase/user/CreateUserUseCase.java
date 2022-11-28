package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;

    public User createUser(User user, String token) {
        user.setCreated(LocalDateTime.now());
        user.setToken(token);
        user.setIsActive(Boolean.TRUE);
        user.setLastLogin(LocalDateTime.now());
        return userRepository.saveUser(user);
    }
}
