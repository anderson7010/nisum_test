package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public void updateUserLoginInfo(String email, String token) {
        User user = userRepository.findUserByEmail(email);
        if (Objects.nonNull(user)) {
            user.setLastLogin(LocalDateTime.now());
            user.setModified(LocalDateTime.now());
            user.setToken(token);
            userRepository.saveUser(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void updateUserState(UUID id, boolean isActive) {
        User user = userRepository.findUserById(id);
        if (Objects.nonNull(user)) {
            user.setModified(LocalDateTime.now());
            user.setIsActive(isActive);
            userRepository.saveUser(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
