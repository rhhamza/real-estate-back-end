package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByFirstname(String name);
    Boolean existsByfirstname (String name);
}
