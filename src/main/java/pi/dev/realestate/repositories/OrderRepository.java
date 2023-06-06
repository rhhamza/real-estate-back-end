package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Order;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{


    List<Order> findByEndDateBeforeAndEndDateAfter(LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
