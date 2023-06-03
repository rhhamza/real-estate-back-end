package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.PublicationComment;
import pi.dev.realestate.repositories.PublicationCommentRepository;
import pi.dev.realestate.services.interfaces.IPublicationCommentService;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationCommentService implements IPublicationCommentService {
    private final PublicationCommentRepository publicationCommentRepository;

    @Autowired
    public PublicationCommentService(PublicationCommentRepository publicationCommentRepository) {
        this.publicationCommentRepository = publicationCommentRepository;
    }

    @Override
    public PublicationComment getPublicationCommentById(int id) {
        Optional<PublicationComment> optionalPublicationComment = publicationCommentRepository.findById(id);
        return optionalPublicationComment.orElse(null);
    }

    @Override
    public List<PublicationComment> getAllPublicationComments() {
        return publicationCommentRepository.findAll();
    }

    @Override
    public List<PublicationComment> getPublicationCommentsByPublicationId(int publicationId) {
        // Implement logic to retrieve comments by publicationId
        // Example: return publicationCommentRepository.findByPublicationId(publicationId);
        return null;
    }

    @Override
    public PublicationComment createPublicationComment(PublicationComment publicationComment) {
        return publicationCommentRepository.save(publicationComment);
    }

    @Override
    public PublicationComment updatePublicationComment(int id, PublicationComment publicationComment) {
        PublicationComment existingComment = getPublicationCommentById(id);
        if (existingComment != null) {
            // Update the existing comment with the new content, user, etc.
            existingComment.setContent(publicationComment.getContent());
            existingComment.setUser(publicationComment.getUser());
            existingComment.setPublication(publicationComment.getPublication());
            return publicationCommentRepository.save(existingComment);
        }
        return null;
    }

    @Override
    public void deletePublicationComment(int id) {
        publicationCommentRepository.deleteById(id);
    }
}
