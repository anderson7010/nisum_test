package co.com.nisum.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDataRepository extends JpaRepository<UserData, UUID> {
    Optional<UserData> findByIdAndIsActiveTrue(UUID id);

    Optional<UserData> findByEmailAndIsActiveTrue(String email);

    List<UserData> findByIsActiveTrue();

    boolean existsByEmailAndIsActiveTrue(String email);
}
