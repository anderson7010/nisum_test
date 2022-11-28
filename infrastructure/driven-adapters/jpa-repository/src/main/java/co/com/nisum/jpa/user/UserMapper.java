package co.com.nisum.jpa.user;

import co.com.nisum.jpa.user.phone.PhoneData;
import co.com.nisum.jpa.user.phone.PhoneMapper;
import co.com.nisum.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PhoneMapper phoneMapper;

    public User toEntity(UserData userData) {
        return User.builder()
                .id(userData.getId())
                .name(userData.getName())
                .email(userData.getEmail())
                .password(userData.getPassword())
                .token(userData.getToken())
                .created(userData.getCreated())
                .modified(userData.getModified())
                .lastLogin(userData.getLastLogin())
                .isActive(userData.getIsActive())
                .phones(userData.getPhones().stream()
                        .map(phoneMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

    public UserData toData(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setToken(user.getToken());
        userData.setCreated(user.getCreated());
        userData.setModified(user.getModified());
        userData.setLastLogin(user.getLastLogin());
        userData.setIsActive(user.getIsActive());
        List<PhoneData> phones = user.getPhones().stream()
                .map(phone -> phoneMapper.toData(userData, phone)).collect(Collectors.toList());
        userData.setPhones(phones);
        return userData;
    }
}
