package pi.dev.realestate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "publication_comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

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