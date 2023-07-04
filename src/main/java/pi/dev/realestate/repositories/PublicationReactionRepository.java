package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Publication;
import pi.dev.realestate.entities.PublicationReaction;
import pi.dev.realestate.entities.ReactionType;
import pi.dev.realestate.entities.UserEntity;

import java.util.List;

@Repository
public interface PublicationReactionRepository extends JpaRepository<PublicationReaction, Integer> {

}
