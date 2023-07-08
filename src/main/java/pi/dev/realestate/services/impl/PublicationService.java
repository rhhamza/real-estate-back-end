package pi.dev.realestate.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.*;
import pi.dev.realestate.entities.DTO.CommentDTO;
import pi.dev.realestate.repositories.PublicationRepository;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.services.interfaces.IPublicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service

public class PublicationService implements IPublicationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PublicationRepository publicationRepository;




    @Override
    public Publication getPublicationById(int id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        return optionalPublication.orElse(null);
    }

    @Override
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public List<Publication> getPublicationsByUserId(int userId) {
        // Implement logic to retrieve publications by userId
        // Example: return publicationRepository.findByUserId(userId);
        return null;
    }

    @Override
    public Publication createPublication(Publication publication, Integer idUser) {
        UserEntity user = userRepository.findById(idUser).orElse(null);
        publication.setUser(user);
       return publicationRepository.save(publication);
    }


    @Override
    public Publication updatePublication(int id, Publication publication) {
        Publication existingPublication = getPublicationById(id);
        if (existingPublication != null) {
            // Update the existing publication with the new title, content, user, etc.
            existingPublication.setTitle(publication.getTitle());
            existingPublication.setContent(publication.getContent());
            existingPublication.setUser(publication.getUser());
            return publicationRepository.save(existingPublication);
        }
        return null;
    }

    @Override
    public void deletePublication(int id) {
        publicationRepository.deleteById(id);
    }


    @Override
    public List<UserEntity> getUsersByReactionType(int publicationId, ReactionType reactionType) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Publication not found"));

        List<UserEntity> users = new ArrayList<>();
        for (PublicationReaction reaction : publication.getReactions()) {
            if (reaction.getReaction() == reactionType) {
                users.add(reaction.getUser());
            }
        }
        return users;
    }

    @Override
    public int getReactionCountByType(int publicationId, ReactionType reactionType) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Publication not found"));

        return (int) publication.getReactions().stream()
                .filter(reaction -> reaction.getReaction() == reactionType)
                .count();
    }

    @Override
    public List<PublicationComment> getAllPublicationComments(int publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Publication not found"));

        return publication.getComments();
    }

    @Override
    public List<CommentDTO> getAllPublicationCommentsWithUser(int publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Publication not found"));

        List<CommentDTO> commentDtos = new ArrayList<>();
        List<PublicationComment> comments = publication.getComments();

        for (PublicationComment comment : comments) {
            UserEntity user = comment.getUser();
            CommentDTO commentDto = new CommentDTO(
                    comment.getId(),
                    comment.getContent(),
                    user.getFirstname(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
            commentDtos.add(commentDto);
        }

        return commentDtos;
    }


}
