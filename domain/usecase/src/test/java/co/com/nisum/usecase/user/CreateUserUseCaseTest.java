package co.com.nisum.usecase.user;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

class CreateUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCreateUserThenSuccess() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .build();

        Mockito.when(userRepository.saveUser(any())).thenReturn(user);

        User resultUser = createUserUseCase.createUser(user, "1234");
        assertEquals("1234", resultUser.getToken());
        assertTrue(resultUser.getIsActive());
        assertTrue(LocalDateTime.now().isAfter(resultUser.getCreated()));
        assertTrue(LocalDateTime.now().isAfter(resultUser.getLastLogin()));
    }
}
