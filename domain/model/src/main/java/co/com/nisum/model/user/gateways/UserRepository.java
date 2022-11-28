package co.com.nisum.model.user.gateways;

import co.com.nisum.model.user.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User saveUser(User user);

    User findUserById(UUID id);

    User findUserByEmail(String email);

    List<User> findUsers();

    boolean existsUserByEmail(String email);
}
