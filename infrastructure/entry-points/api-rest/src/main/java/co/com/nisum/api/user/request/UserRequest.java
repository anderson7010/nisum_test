package co.com.nisum.api.user.request;

import co.com.nisum.model.user.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String token;
    private List<PhoneRequest> phones;

    public User toModel(PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .token(token)
                .phones(phones.stream().map(PhoneRequest::toModel).collect(Collectors.toList()))
                .build();
    }
}
