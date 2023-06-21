package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.services.interfaces.IConversationService;

import java.util.List;

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
}

