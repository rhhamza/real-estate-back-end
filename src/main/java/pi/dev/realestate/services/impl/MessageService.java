package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.Message;
import pi.dev.realestate.exceptions.MessageNotFoundException;
import pi.dev.realestate.repositories.MessageRepository;
import pi.dev.realestate.services.interfaces.IMessageService;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesByConversation(Long conversation) {
        return messageRepository.findByConversation_IdOrderByTimestamp(conversation);
    }

    @Override
    public void deleteMessage(Long messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            throw new MessageNotFoundException("Message not found with ID: " + messageId);
        }
        Message message = optionalMessage.get();
        messageRepository.delete(message);
    }


}
