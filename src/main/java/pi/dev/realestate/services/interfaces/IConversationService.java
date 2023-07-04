package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.Message;
import pi.dev.realestate.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IConversationService {
    Conversation createConversation(Conversation conversation);

    List<Conversation> getConversationsByUser(int user);

    Optional<Conversation> getConversationByIdAndUser(Long conversationId, Company user);
    Conversation addParticipant(Long conversationId, UserEntity user);

    Conversation updateConversation(Long conversationId, Message message);

    Conversation getConversationbyId(Long id);

    Optional<Conversation> getConversationByUserFirstNamAndLastName(String firstName, String lastName);
    Optional<Conversation> getConversationByUserFirstNameOrLastName(String firstName, String lastName);

    List<Conversation> getConversations();
}
