package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
