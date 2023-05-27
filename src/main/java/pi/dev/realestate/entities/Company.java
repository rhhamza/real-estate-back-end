package pi.dev.realestate.entities;

import lombok.*;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String address;
    private String description;
    private int phone;
    private String email;
    private String logo;
    private StatusType status;
    private Timestamp timestamp;

}
