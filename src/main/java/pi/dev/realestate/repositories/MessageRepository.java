package pi.dev.realestate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.dev.realestate.entities.Conversation;
import pi.dev.realestate.entities.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversation_IdOrderByTimestamp(Long conversation);
}
