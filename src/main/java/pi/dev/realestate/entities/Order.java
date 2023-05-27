package pi.dev.realestate.entities;

import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private StatusType status;
    private Timestamp timestamp;

   /*
    StartDate: DateTime
    EndDate: DateTime
    Status: StatusType
    CreatedAt: DateTime
    UpdatedAt: DateTime*/
}
