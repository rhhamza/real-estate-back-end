package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.CompanyImage;
import pi.dev.realestate.entities.Order;

import java.util.List;

@Repository
public interface CompanyImageRepository extends JpaRepository<CompanyImage, Long> {


    List<Order> findOrdersByCompanyId(Integer companyId);
    CompanyImage findCompanyImageByCompanyId(Integer id);
}
