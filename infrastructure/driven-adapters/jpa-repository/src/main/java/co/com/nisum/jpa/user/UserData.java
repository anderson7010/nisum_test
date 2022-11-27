package co.com.nisum.jpa.user;

import co.com.nisum.jpa.user.phone.PhoneData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String token;
    private LocalDateTime created;
    private LocalDateTime modified;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneData> phones = new ArrayList<>();
}
