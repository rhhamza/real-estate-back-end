package pi.dev.realestate.entities;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Appointment  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppointment;
    private String title;
    private String discrition;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String MeetingLink;
    private boolean online;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        //updatedAt = new Timestamp(System.currentTimeMillis());
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    private Company company;
    @ManyToOne
    private PropertyOffer propertyOffer;

}