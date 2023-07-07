package pi.dev.realestate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.EnumType;
import java.sql.Timestamp;

@Entity
@Table(name = "publication_reactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ReactionType reaction;

    @ManyToOne
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @JsonIgnore
    private Publication publication;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}