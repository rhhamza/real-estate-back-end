package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
