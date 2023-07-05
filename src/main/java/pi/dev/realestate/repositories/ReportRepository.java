package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.Report;

public interface ReportRepository extends JpaRepository<Report,Integer> {

}
