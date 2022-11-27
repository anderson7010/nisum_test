package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import co.com.nisum.model.user.gateways.UserTokenRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    public User createUser(User user) {
        user.setCreated(LocalDateTime.now());
        user.setToken(userTokenRepository.generateToken(user.getEmail()));
        user.setIsActive(Boolean.TRUE);
        user.setLastLogin(LocalDateTime.now());
        return userRepository.saveUser(user);
    }
}
