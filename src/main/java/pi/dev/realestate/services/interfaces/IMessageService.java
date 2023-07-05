package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.Message;

import java.util.List;

public interface IMessageService {
    Message createMessage(Message message);

    List<Message> getMessagesByConversation(Long conversation);
    void deleteMessage(Long messageId);
}
