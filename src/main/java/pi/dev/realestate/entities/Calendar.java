package pi.dev.realestate.entities;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Calendar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idCalendar;


    private LocalDate date;


    private LocalDateTime startTime;


    private LocalDateTime endTime;

    //@OneToMany (mappedBy = "calendar", cascade = CascadeType.ALL)
   // private List<Appointment> appointments;
}
