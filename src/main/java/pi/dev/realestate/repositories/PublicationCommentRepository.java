package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.PublicationComment;

@Repository
public interface PublicationCommentRepository extends JpaRepository<PublicationComment, Integer> {
}
