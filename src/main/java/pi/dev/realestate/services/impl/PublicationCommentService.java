package pi.dev.realestate.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.BadWords;
import pi.dev.realestate.entities.Publication;
import pi.dev.realestate.entities.PublicationComment;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.BadWordsRepository;
import pi.dev.realestate.repositories.PublicationCommentRepository;
import pi.dev.realestate.repositories.PublicationRepository;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.services.interfaces.IPublicationCommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PublicationCommentService implements IPublicationCommentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    BadWordsRepository badWordsRepository;
    @Autowired
   PublicationCommentRepository publicationCommentRepository;



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
    public PublicationComment createPublicationComment(PublicationComment publicationComment, Integer idUser, Integer idPublication) {
        UserEntity user = userRepository.findById(idUser).orElse(null);
        Publication publication = publicationRepository.findById(idPublication).orElse(null);
        publicationComment.setUser(user);
        publicationComment.setPublication(publication);
        String sanitizedComment = sanitizeComment(publicationComment.getContent());
        publicationComment.setContent(sanitizedComment);
        return publicationCommentRepository.save(publicationComment);
    }


     private String sanitizeComment(String comment) {
        List<String> prohibitedWords = getProhibitedWords();
        return prohibitedWords.stream()
                .reduce(comment, (c, word) -> c.replaceAll(word, "*".repeat(word.length())));
    }


    private List<String> getProhibitedWords() {
        List<String> prohibitedWords = new ArrayList<>();
        List<BadWords> prohibitedWordEntities = badWordsRepository.findAll();
        for (BadWords prohibitedWord : prohibitedWordEntities) {
            prohibitedWords.add(prohibitedWord.getWord());
        }
        return prohibitedWords;
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