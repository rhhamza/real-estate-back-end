package pi.dev.realestate.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<UserEntity> participants;
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("conversation")
    private List<Message> messages;
}
