package pi.dev.realestate.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
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
    private Date createdAt;
    private Date updatedAt;

   /*
    StartDate: DateTime
    EndDate: DateTime
    Status: StatusType
    CreatedAt: DateTime
    UpdatedAt: DateTime*/
}
