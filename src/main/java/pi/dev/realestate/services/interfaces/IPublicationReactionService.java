package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.PublicationReaction;

import java.util.List;

public interface IPublicationReactionService {
    PublicationReaction getPublicationReactionById(int id);
    List<PublicationReaction> getAllPublicationReactions();
    List<PublicationReaction> getPublicationReactionsByPublicationId(int publicationId);
    PublicationReaction createPublicationReaction(PublicationReaction publicationReaction);
    PublicationReaction updatePublicationReaction(int id, PublicationReaction publicationReaction);
    void deletePublicationReaction(int id);
}
