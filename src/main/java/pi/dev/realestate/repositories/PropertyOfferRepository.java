package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.PropertyOffer;
@Repository
public interface PropertyOfferRepository extends JpaRepository<PropertyOffer, Integer> {
}
