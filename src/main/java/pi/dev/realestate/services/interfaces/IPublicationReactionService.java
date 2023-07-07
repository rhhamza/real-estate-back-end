package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.PublicationReaction;
import pi.dev.realestate.entities.ReactionType;
import pi.dev.realestate.entities.UserEntity;

import java.util.List;
import java.util.Map;

public interface IPublicationReactionService {
    PublicationReaction getPublicationReactionById(int id);
    List<PublicationReaction> getAllPublicationReactions();
    List<PublicationReaction> getPublicationReactionsByPublicationId(int publicationId);
    PublicationReaction createPublicationReaction(PublicationReaction publicationReaction, Integer idUser, Integer idPublication);
    PublicationReaction updatePublicationReaction(int id, PublicationReaction publicationReaction);
    void deletePublicationReaction(int id);
    PublicationReaction getReactionByUserAndPublication(Integer userId, Integer publicationId);
}
