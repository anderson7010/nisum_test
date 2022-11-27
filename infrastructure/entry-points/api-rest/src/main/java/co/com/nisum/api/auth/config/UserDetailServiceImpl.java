package co.com.nisum.api.auth.config;

import co.com.nisum.model.user.User;
import co.com.nisum.usecase.user.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final GetUserUseCase getUserUseCase;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserUseCase.getUserByEmail(email);
        if (Objects.nonNull(user)) {
            return new UserDetailsImpl(user);
        } else {
            throw new UsernameNotFoundException("User " + email + " not found.");
        }
    }
}
