package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.DTO.CommentDTO;
import pi.dev.realestate.entities.Publication;
import pi.dev.realestate.entities.PublicationComment;
import pi.dev.realestate.entities.ReactionType;
import pi.dev.realestate.entities.UserEntity;

import java.util.List;

public interface IPublicationService {
    Publication getPublicationById(int id);
    List<Publication> getAllPublications();
    List<Publication> getPublicationsByUserId(int userId);
    Publication createPublication(Publication publication , Integer idUser);
    Publication updatePublication(int id, Publication publication);
    void deletePublication(int id);
    List<UserEntity> getUsersByReactionType(int publicationId, ReactionType reactionType);

    int getReactionCountByType(int publicationId, ReactionType reactionType);

    List<PublicationComment> getAllPublicationComments(int publicationId);

    List<CommentDTO> getAllPublicationCommentsWithUser(int publicationId);
}
