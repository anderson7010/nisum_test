package co.com.nisum.api.user.request;

import co.com.nisum.api.common.config.RegexProperties;
import co.com.nisum.model.user.User;
import co.com.nisum.model.validation.ValidationResult;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserRequest {
    @NotNull
    @Size(min = 1, max = 150, message = " max length is 150 characters.")
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Valid
    private List<PhoneRequest> phones;

    public User toModel(PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .phones(phones.stream().map(PhoneRequest::toModel).collect(Collectors.toList()))
                .build();
    }

    public ValidationResult isValid(RegexProperties regexProperties) {
        if (!ValidationResult.patternMatches(email, regexProperties.getEmailRegex())) {
            return ValidationResult.builder(Boolean.FALSE, "Invalid format email.");
        }
        if (!ValidationResult.patternMatches(password, regexProperties.getPasswordRegex())) {
            return ValidationResult.builder(Boolean.FALSE, "Invalid format password.");
        }
        return ValidationResult.builder(Boolean.TRUE, null);
    }
}
