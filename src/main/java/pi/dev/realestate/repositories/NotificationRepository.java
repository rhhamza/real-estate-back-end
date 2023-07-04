package pi.dev.realestate.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Notification;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientIdOrderByTimestamp(int recipient);

    long countByRecipientIdAndIsRead(int recipient, boolean isRead);
}
