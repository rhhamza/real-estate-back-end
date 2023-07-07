package pi.dev.realestate.entities;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date timestamp;
    @ManyToOne
    private UserEntity sender;
    @ManyToOne
    private Conversation conversation;

}
