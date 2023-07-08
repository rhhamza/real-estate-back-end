package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail (String email);

    List<UserEntity> findUserEntityByFirstname(String firstname);
}
