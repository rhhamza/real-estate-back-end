package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.Roles;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String name);
    Boolean existsByName (String name);
}
