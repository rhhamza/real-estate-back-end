package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.Message;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.services.interfaces.IConversationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conversations")
public class ConversationController {
    @Autowired
    private IConversationService iconversationService;

    @PostMapping
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation) {
        Conversation createdConversation = iconversationService.createConversation(conversation);
        return ResponseEntity.ok(createdConversation);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Conversation>> getConversationsByUser(@PathVariable int userId) {
        List<Conversation> conversations = iconversationService.getConversationsByUser(userId);
        return ResponseEntity.ok(conversations);
    }

    @PostMapping("/{conversationId}")
    public ResponseEntity<Conversation> addParticipant(@PathVariable Long conversationId, @RequestBody UserEntity user) {
        Conversation updatedConversation = iconversationService.addParticipant(conversationId, user);
        return ResponseEntity.ok(updatedConversation);
    }

    @PutMapping("/{conversationId}")
    public ResponseEntity<Conversation> updateConversation(
            @PathVariable Long conversationId,
            @RequestBody Message message) {
        Conversation updatedConversation = iconversationService.updateConversation(conversationId, message);
        return ResponseEntity.ok(updatedConversation);
    }

    @GetMapping("/{conversationId}")
    public Conversation getConversationByid(@PathVariable("conversationById") Long id){
        return iconversationService.getConversationbyId(id);
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<?> getConversationByParticipantName(
            @PathVariable String firstName,
            @PathVariable String lastName
    ) {
        try {
            Optional<Conversation> conversation = iconversationService.getConversationByUserFirstNamAndLastName(firstName, lastName);

            if (conversation.isPresent()) {
                return ResponseEntity.ok(conversation.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving conversation");
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> getConversationByUserFirstNameOrLastName(
            @RequestParam String name
    ) {
        try {
            Optional<Conversation> conversation = iconversationService.getConversationByUserFirstNameOrLastName(name);

            if (conversation.isPresent()) {
                return ResponseEntity.ok(conversation.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving conversation");
        }
    }

    @GetMapping("/")
    public List<Conversation> getConversations(){
        return iconversationService.getConversations();
    }
}

