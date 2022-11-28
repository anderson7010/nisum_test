package co.com.nisum.usecase.user;

import co.com.nisum.model.exception.UserNotFoundException;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public void deleteUser(UUID id) throws UserNotFoundException {
        User user = userRepository.findUserById(id);
        if (Objects.nonNull(user)) {
            user.setModified(LocalDateTime.now());
            user.setIsActive(Boolean.FALSE);
            userRepository.saveUser(user);
        } else {
            throw new UserNotFoundException();
        }
    }
}
