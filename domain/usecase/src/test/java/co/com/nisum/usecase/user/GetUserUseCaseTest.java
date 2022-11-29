package co.com.nisum.usecase.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class GetUserUseCaseTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    GetUserUseCase getUserUseCase;

    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .id(UUID.fromString("61d9cfa2-edfe-45d2-8466-0ab90dd5f86e"))
                .name("Peter")
                .email("peter@gmail.com")
                .build();
    }

    @Test
    void whenGetUserByEmailThenSuccess() {
        Mockito.when(userRepository.findUserByEmail(any())).thenReturn(user);

        User resultUser = getUserUseCase.getUserByEmail("peter@gmail.com");

        assertNotNull(resultUser);
        assertEquals("61d9cfa2-edfe-45d2-8466-0ab90dd5f86e", resultUser.getId().toString());
        assertEquals("Peter", resultUser.getName());
        assertEquals("peter@gmail.com", resultUser.getEmail());
    }

    @Test
    void whenGetUsersThenSuccess() {
        Mockito.when(userRepository.findUsers()).thenReturn(Collections.singletonList(user));

        List<User> users = getUserUseCase.getUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void whenExistsUserByEmailThenSuccess() {
        Mockito.when(userRepository.existsUserByEmail(any())).thenReturn(true);

        assertTrue(getUserUseCase.existsUserByEmail("peter@gmail.com"));
    }
}
