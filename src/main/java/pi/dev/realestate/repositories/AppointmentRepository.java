package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Appointment;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}