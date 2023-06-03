package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.PublicationReaction;
import pi.dev.realestate.repositories.PublicationReactionRepository;
import pi.dev.realestate.services.interfaces.IPublicationReactionService;
import pi.dev.realestate.services.interfaces.IPublicationService;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationReactionService implements IPublicationReactionService {
    private final PublicationReactionRepository publicationReactionRepository;

    @Autowired
    public PublicationReactionService(PublicationReactionRepository publicationReactionRepository) {
        this.publicationReactionRepository = publicationReactionRepository;
    }

    @Override
    public PublicationReaction getPublicationReactionById(int id) {
        Optional<PublicationReaction> optionalPublicationReaction = publicationReactionRepository.findById(id);
        return optionalPublicationReaction.orElse(null);
    }

    @Override
    public List<PublicationReaction> getAllPublicationReactions() {
        return publicationReactionRepository.findAll();
    }

    @Override
    public List<PublicationReaction> getPublicationReactionsByPublicationId(int publicationId) {
        // Implement logic to retrieve reactions by publicationId
        // Example: return publicationReactionRepository.findByPublicationId(publicationId);
        return null;
    }

    @Override
    public PublicationReaction createPublicationReaction(PublicationReaction publicationReaction) {
        return publicationReactionRepository.save(publicationReaction);
    }

    @Override
    public PublicationReaction updatePublicationReaction(int id, PublicationReaction publicationReaction) {
        PublicationReaction existingReaction = getPublicationReactionById(id);
        if (existingReaction != null) {
            // Update the existing reaction with the new reaction type, user, etc.
            existingReaction.setReaction(publicationReaction.getReaction());
            existingReaction.setUser(publicationReaction.getUser());
            existingReaction.setPublication(publicationReaction.getPublication());
            return publicationReactionRepository.save(existingReaction);
        }
        return null;
    }

    @Override
    public void deletePublicationReaction(int id) {
        publicationReactionRepository.deleteById(id);
    }
}
