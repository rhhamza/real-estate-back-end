package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Appointment;
import pi.dev.realestate.entities.PropertyOffer;
import pi.dev.realestate.entities.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.propertyOffer = :propertyOffer " +
            "AND ((a.dateDebut >= :dateDebut AND a.dateDebut <= :dateFin) " +
            "OR (a.dateFin >= :dateDebut AND a.dateFin <= :dateFin) " +
            "OR (a.dateDebut <= :dateDebut AND a.dateFin >= :dateFin))")
    List<Appointment> findByPropertyOfferAndDateRange(@Param("propertyOffer") PropertyOffer propertyOffer,
                                                      @Param("dateDebut") LocalDateTime dateDebut,
                                                      @Param("dateFin") LocalDateTime dateFin);
    @Query("SELECT a FROM Appointment a WHERE a.user = :user " +
            "AND ((a.dateDebut >= :dateDebut AND a.dateDebut <= :dateFin) " +
            "OR (a.dateFin >= :dateDebut AND a.dateFin <= :dateFin) " +
            "OR (a.dateDebut <= :dateDebut AND a.dateFin >= :dateFin))")
    List<Appointment> findByUserAndDateRange(@Param("user") UserEntity user,
                                             @Param("dateDebut") LocalDateTime dateDebut,
                                             @Param("dateFin") LocalDateTime dateFin);
}
