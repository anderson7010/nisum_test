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

class UpdateUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UpdateUserUseCase updateUserUseCase;

    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Test
    void whenUpdateUserLoginInfoThenSuccess() throws UserNotFoundException {
        Mockito.when(userRepository.findUserByEmail(any())).thenReturn(user);
        Mockito.when(userRepository.saveUser(any())).thenReturn(user);

        User resultUser = updateUserUseCase.updateUserLoginInfo("peter@gmail.com", "1234");

        assertEquals("1234", resultUser.getToken());
        assertTrue(LocalDateTime.now().isAfter(resultUser.getLastLogin()));
        assertTrue(LocalDateTime.now().isAfter(resultUser.getModified()));
    }

    @Test
    void whenUpdateUserLoginInfoThenException() {
        Mockito.when(userRepository.findUserById(any())).thenReturn(null);

        assertThrows(UserNotFoundException.class,() ->
                updateUserUseCase.updateUserLoginInfo("peter@gmail.com", "1234"));
    }
}
