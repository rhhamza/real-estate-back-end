package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Order;
import pi.dev.realestate.entities.StatusType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


    List<Order> findOrdersByCompanyId(Integer companyId);

    List<Order> findByStartDateBetween(Date startDate, Date endDate);

    List<Order> findByEndDateBeforeAndEndDateAfter(LocalDateTime currentDate, LocalDateTime thresholdDate);

    List<Order> findByEndDateBefore(LocalDate endDate);

    @Query("SELECT o FROM Order o WHERE o.endDate <= :endDateThreshold")
    List<Order> getOrdersWithEndDateBefore(@Param("endDateThreshold") LocalDate endDateThreshold);

    List<Order> findByStatus(StatusType status);

}

