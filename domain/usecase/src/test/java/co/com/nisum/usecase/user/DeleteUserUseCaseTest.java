package co.com.nisum.usecase.user;

import co.com.nisum.model.exception.UserNotFoundException;
import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DeleteUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    DeleteUserUseCase deleteUserUseCase;

    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Test
    void whenDeleteUserThenSuccess() throws UserNotFoundException {
        Mockito.when(userRepository.findUserById(any())).thenReturn(user);
        Mockito.when(userRepository.saveUser(any())).thenReturn(user);

        User resultUser = deleteUserUseCase.deleteUser(UUID.randomUUID());

        assertFalse(resultUser.getIsActive());
        assertTrue(LocalDateTime.now().isAfter(resultUser.getModified()));
    }

    @Test
    void whenDeleteUserThenException() {
        Mockito.when(userRepository.findUserById(any())).thenReturn(null);

        assertThrows(UserNotFoundException.class,() ->
                deleteUserUseCase.deleteUser(UUID.randomUUID()));
    }
}
