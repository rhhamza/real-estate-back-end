package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.PublicationComment;

import java.util.List;

public interface IPublicationCommentService {
    PublicationComment getPublicationCommentById(int id);
    List<PublicationComment> getAllPublicationComments();
    List<PublicationComment> getPublicationCommentsByPublicationId(int publicationId);
    PublicationComment createPublicationComment(PublicationComment publicationComment,Integer idUser, Integer idPublication);
    PublicationComment updatePublicationComment(int id, PublicationComment publicationComment);
    void deletePublicationComment(int id);
}
