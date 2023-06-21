package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IConversationService {
    Conversation createConversation(Conversation conversation);

    List<Conversation> getConversationsByUser(int user);

    Optional<Conversation> getConversationByIdAndUser(Long conversationId, Company user);
    Conversation addParticipant(Long conversationId, UserEntity user);

}
