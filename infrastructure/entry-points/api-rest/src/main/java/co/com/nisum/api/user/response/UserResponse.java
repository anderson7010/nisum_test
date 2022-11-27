package co.com.nisum.api.user.response;

import co.com.nisum.model.user.User;
import co.com.nisum.model.user.phone.Phone;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private Boolean isActive;
    private List<Phone> phones;

    public UserResponse(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        token = user.getToken();
        created = user.getCreated();
        modified = user.getModified();
        lastLogin = user.getLastLogin();
        isActive = user.getIsActive();
        phones = user.getPhones();
    }
}
