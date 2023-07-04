// package pi.dev.realestate.services.impl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import pi.dev.realestate.entities.Company;
// import pi.dev.realestate.entities.Notification;
// import pi.dev.realestate.exceptions.NotificationNotFoundException;
// import pi.dev.realestate.repositories.NotificationRepository;
// import pi.dev.realestate.services.interfaces.INotificationService;

// import java.util.List;

// @Service
// public class NotificationService implements INotificationService {

//     @Autowired
//     private NotificationRepository notificationRepository;

//     @Override
//     public Notification createNotification(Notification notification) {
//         return notificationRepository.save(notification);
//     }

//     @Override
//     public List<Notification> getNotificationsByRecipient(int recipient) {
//         return notificationRepository.findByRecipientIdOrderByTimestamp(recipient);
//     }

//     @Override
//     public long countUnreadNotifications(int recipient) {
//         return notificationRepository.countByRecipientIdAndIsRead(recipient, false);
//     }

//     @Override
//     public void markNotificationAsRead(Long notificationId) {
//         Notification notification = notificationRepository.findById(notificationId)
//                 .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + notificationId));

//         if (!notification.isRead()) {
//             notification.setRead(true);
//             notificationRepository.save(notification);
//         }
//     }
// }
