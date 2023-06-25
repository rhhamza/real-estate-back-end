package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.StatusType;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE EXISTS (SELECT o FROM Order o WHERE o.company = c AND o.status = :status)")
    List<Company> findCompaniesWithOrdersByStatus(@Param("status") StatusType status);
}