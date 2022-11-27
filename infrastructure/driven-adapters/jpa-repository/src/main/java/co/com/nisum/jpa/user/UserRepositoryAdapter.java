package co.com.nisum.jpa.user;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserDataRepository repository;
    private final UserMapper userMapper;

    @Override
    public User saveUser(User user) {
        return userMapper.toEntity(repository.save(userMapper.toData(user)));
    }

    @Override
    @Transactional
    public User findUserById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id).map(userMapper::toEntity).orElse(null);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return repository.findByEmailAndIsActiveTrue(email).map(userMapper::toEntity).orElse(null);
    }

    @Override
    public List<User> findUsers() {
        return repository.findByIsActiveTrue().stream().map(userMapper::toEntity).collect(Collectors.toList());
    }
}
