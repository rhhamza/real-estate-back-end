package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.CompanyImage;

@Repository
public interface CompanyImageRepository extends JpaRepository<CompanyImage, Long> {
}
