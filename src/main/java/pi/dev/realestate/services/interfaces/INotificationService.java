package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Notification;

import java.util.List;

public interface INotificationService {
    Notification createNotification(Notification notification);

    List<Notification> getNotificationsByRecipient(int recipient);

    long countUnreadNotifications(int recipient);

    void markNotificationAsRead(Long notificationId);
}
