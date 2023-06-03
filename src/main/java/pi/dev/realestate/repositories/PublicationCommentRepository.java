package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.PublicationComment;

public interface PublicationCommentRepository extends JpaRepository<PublicationComment, Integer> {
}
