package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.PublicationReaction;

public interface PublicationReactionRepository extends JpaRepository<PublicationReaction, Integer> {
}
