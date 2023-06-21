package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.exceptions.ConversationNotFoundException;
import pi.dev.realestate.exceptions.ParticipantExistsException;
import pi.dev.realestate.repositories.ConversationRepository;
import pi.dev.realestate.services.interfaces.IConversationService;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService implements IConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation createConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public List<Conversation> getConversationsByUser(int company) {
        return conversationRepository.findByParticipantsId(company);
    }

    @Override
    public Optional<Conversation> getConversationByIdAndUser(Long conversationId, Company company) {
        return conversationRepository.findByIdAndParticipants(conversationId, company);
    }
    @Override
    public Conversation addParticipant(Long conversationId, UserEntity user) {
        Optional<Conversation> optionalConversation = conversationRepository.findById(conversationId);
        if (optionalConversation.isEmpty()) {
            throw new ConversationNotFoundException("Conversation not found with ID: " + conversationId);
        }
        Conversation conversation = optionalConversation.get();
        if (conversation.getParticipants().contains(user)) {
            throw new ParticipantExistsException("User is already a participant in the conversation");
        }
        conversation.getParticipants().add(user);
        Conversation updatedConversation = conversationRepository.save(conversation);

        return updatedConversation;
    }
}
