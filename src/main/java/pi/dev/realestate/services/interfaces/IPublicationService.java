package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Publication;

import java.util.List;

public interface IPublicationService {
    Publication getPublicationById(int id);
    List<Publication> getAllPublications();
    List<Publication> getPublicationsByUserId(int userId);
    Publication createPublication(Publication publication);
    Publication updatePublication(int id, Publication publication);
    void deletePublication(int id);
}
