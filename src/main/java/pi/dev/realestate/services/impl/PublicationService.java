package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Publication;
import pi.dev.realestate.repositories.PublicationRepository;
import pi.dev.realestate.services.interfaces.IPublicationService;

import java.util.List;
import java.util.Optional;


@Service
public class PublicationService implements IPublicationService {
    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

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
    public Publication createPublication(Publication publication) {
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

}
