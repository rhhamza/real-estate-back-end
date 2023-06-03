package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {

}