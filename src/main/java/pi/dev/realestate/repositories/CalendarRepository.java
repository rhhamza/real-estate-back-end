package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}