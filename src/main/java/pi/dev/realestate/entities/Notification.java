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
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date timestamp;
    private boolean isRead;
    @ManyToOne
    private UserEntity recipient;
}

