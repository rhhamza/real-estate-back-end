package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Conversation;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByParticipantsId(int company);

    Optional<Conversation> findByIdAndParticipants(Long conversationId, Company company);
}
