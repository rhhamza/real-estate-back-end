package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.PublicationComment;
import pi.dev.realestate.services.impl.PublicationCommentService;

import java.util.List;

@RestController
@RequestMapping("/publicationComments")
public class PublicationCommentController {
    private final PublicationCommentService commentService;

    @Autowired

    public PublicationCommentController(PublicationCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationComment> getCommentById(@PathVariable int id) {
        PublicationComment comment = commentService.getPublicationCommentById(id);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<PublicationComment>> getAllComments() {
        List<PublicationComment> comments = commentService.getAllPublicationComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublicationComment> createComment(@RequestBody PublicationComment comment) {
        PublicationComment createdComment = commentService.createPublicationComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationComment> updateComment(@PathVariable int id, @RequestBody PublicationComment comment) {
        PublicationComment updatedComment = commentService.updatePublicationComment(id, comment);
        if (updatedComment != null) {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        commentService.deletePublicationComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
