package pi.dev.realestate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.DTO.CommentDTO;
import pi.dev.realestate.entities.DTO.PublicationDTO;
import pi.dev.realestate.entities.Publication;
import pi.dev.realestate.entities.PublicationComment;
import pi.dev.realestate.entities.ReactionType;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.services.impl.PublicationService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable int id) {
        Publication publication = publicationService.getPublicationById(id);
        if (publication != null) {
            return new ResponseEntity<>(publication, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications() {
        List<Publication> publications = publicationService.getAllPublications();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @PostMapping("/add/{idUser}")
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication ,
                                                         @PathVariable("idUser") Integer idUser) {
        log.info(idUser.toString());
        Publication createdPublication = publicationService.createPublication(publication,idUser);

       return new ResponseEntity<>(createdPublication, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable int id, @RequestBody Publication publication) {
        Publication updatedPublication = publicationService.updatePublication(id, publication);
        if (updatedPublication != null) {
            return new ResponseEntity<>(updatedPublication, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable int id) {
        publicationService.deletePublication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{publicationId}/users/{reactionType}")
    public ResponseEntity<List<UserEntity>> getUsersByReactionType(
            @PathVariable int publicationId, @PathVariable ReactionType reactionType) {
        List<UserEntity> users = publicationService.getUsersByReactionType(publicationId, reactionType);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{publicationId}/reactions/count")
    public ResponseEntity<Integer> getReactionCountByType(
            @PathVariable("publicationId") int publicationId,
            @RequestParam("reactionType") ReactionType reactionType
    ) {
        int count = publicationService.getReactionCountByType(publicationId, reactionType);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{publicationId}/comments")
    public ResponseEntity<List<PublicationComment>> getAllPublicationComments(
            @PathVariable int publicationId
    ) {
        List<PublicationComment> comments = publicationService.getAllPublicationComments(publicationId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{publicationId}/commentsWithuser")
    public List<CommentDTO> getAllPublicationCommentsWithUser(@PathVariable int publicationId) {
        return publicationService.getAllPublicationCommentsWithUser(publicationId);
    }


    @GetMapping("/fullpublications")
    public ResponseEntity<List<PublicationDTO>> getAllFullPublications() {
        List<PublicationDTO> publications = publicationService.getAllFullPublications();
        return ResponseEntity.ok(publications);
    }
}