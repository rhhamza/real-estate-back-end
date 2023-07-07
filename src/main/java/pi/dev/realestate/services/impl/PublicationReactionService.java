package pi.dev.realestate.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Publication;
import pi.dev.realestate.entities.PublicationReaction;
import pi.dev.realestate.entities.ReactionType;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.PublicationReactionRepository;
import pi.dev.realestate.repositories.PublicationRepository;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.services.interfaces.IPublicationReactionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PublicationReactionService implements IPublicationReactionService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    PublicationReactionRepository publicationReactionRepository;




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
    public PublicationReaction createPublicationReaction(PublicationReaction publicationReaction,  Integer idUser, Integer idPublication) {
        UserEntity user = userRepository.findById(idUser).orElse(null);
        Publication publication = publicationRepository.findById(idPublication).orElse(null);
        publicationReaction.setUser(user);
        publicationReaction.setPublication(publication);
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

    @Override
    public PublicationReaction getReactionByUserAndPublication(Integer userId, Integer publicationId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        Publication publication = publicationRepository.findById(publicationId).orElse(null);

        if (user != null && publication != null) {
            PublicationReaction reaction = publicationReactionRepository.findByUserAndPublication(user, publication);
            if (reaction != null) {
                reaction.getUser().setID(userId); // Set the userId in the returned reaction
            }
            return reaction;
        }

        return null;
    }
}
