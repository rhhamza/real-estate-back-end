package pi.dev.realestate.entities.DTO;

import lombok.*;
import pi.dev.realestate.entities.Publication;
import pi.dev.realestate.entities.PublicationComment;
import pi.dev.realestate.entities.PublicationReaction;
import pi.dev.realestate.entities.UserEntity;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDTO {

    private int id;

    private String title;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private UserEntity user;

    private List<PublicationComment> comments;

    private List<PublicationReaction> reactions;

    public static PublicationDTO fromPublication(Publication publication) {
        return new PublicationDTO(
                publication.getId(),
                publication.getTitle(),
                publication.getContent(),
                publication.getCreatedAt(),
                publication.getUpdatedAt(),
                publication.getUser(),
                publication.getComments(),
                publication.getReactions()
        );
    }


}