package pi.dev.realestate.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pi.dev.realestate.entities.PublicationComment;
import pi.dev.realestate.entities.PublicationReaction;
import pi.dev.realestate.entities.UserEntity;

import java.sql.Timestamp;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {
    private int id;
    private String title;
    private String content;
    private UserEntity user;
    private List<PublicationComment> comments;
    private List<PublicationReaction> reactions;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
