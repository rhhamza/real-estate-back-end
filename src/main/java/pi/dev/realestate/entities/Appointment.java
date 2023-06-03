package pi.dev.realestate.entities;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppointment;
    private LocalDateTime date;
    private int duration;
    private String location;
    @ManyToOne
    private Calendar calendar;
    @ManyToOne
    private PropertyOffer propertyOffer;
}
