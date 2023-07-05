package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.Message;
import pi.dev.realestate.services.interfaces.IMessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private IMessageService imessageService;

    @PostMapping("/add")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message sentMessage = imessageService.createMessage(message);
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<Message>> getMessagesByConversation(@PathVariable Long conversationId) {
        List<Message> messages = imessageService.getMessagesByConversation(conversationId);
        return ResponseEntity.ok(messages);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        imessageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }
}
