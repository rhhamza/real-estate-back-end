package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Conversation;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByParticipantsID(int company);
    Optional<Conversation> findById(Long id);
    Optional<Conversation> findByIdAndParticipants(Long conversationId, Company company);
    Optional<Conversation> findByParticipantsFirstnameAndParticipantsLastname(String firstName, String lastName);
    Optional<Conversation> findByParticipantsFirstnameOrParticipantsLastname(String firstName, String lastName);

}
