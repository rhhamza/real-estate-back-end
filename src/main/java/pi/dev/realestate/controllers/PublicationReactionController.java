package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.PublicationReaction;
import pi.dev.realestate.services.impl.PublicationReactionService;

import java.util.List;

@RestController
@RequestMapping("/publicationReactions")
public class PublicationReactionController {
    private final PublicationReactionService reactionService;

    @Autowired
    public PublicationReactionController(PublicationReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationReaction> getReactionById(@PathVariable int id) {
        PublicationReaction reaction = reactionService.getPublicationReactionById(id);
        if (reaction != null) {
            return new ResponseEntity<>(reaction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<PublicationReaction>> getAllReactions() {
        List<PublicationReaction> reactions = reactionService.getAllPublicationReactions();
        return new ResponseEntity<>(reactions, HttpStatus.OK);
    }

    @PostMapping("/add/{idUser}/{idPublication}")
    public ResponseEntity<PublicationReaction> createReaction(@RequestBody PublicationReaction reaction ,
                                                              @PathVariable("idUser") Integer idUser,
                                                              @PathVariable("idPublication") Integer idPublication) {
        PublicationReaction createdReaction = reactionService.createPublicationReaction(reaction , idUser, idPublication);
        return new ResponseEntity<>(createdReaction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationReaction> updateReaction(@PathVariable int id, @RequestBody PublicationReaction reaction) {
        PublicationReaction updatedReaction = reactionService.updatePublicationReaction(id, reaction);
        if (updatedReaction != null) {
            return new ResponseEntity<>(updatedReaction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable int id) {
        reactionService.deletePublicationReaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}/publication/{publicationId}")
    public ResponseEntity<PublicationReaction> getReactionByUserAndPublication(
            @PathVariable("userId") Integer userId,
            @PathVariable("publicationId") Integer publicationId) {
        PublicationReaction reaction = reactionService.getReactionByUserAndPublication(userId, publicationId);
        if (reaction != null) {
            return new ResponseEntity<>(reaction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
