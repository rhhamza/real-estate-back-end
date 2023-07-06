package pi.dev.realestate.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Notification;
import pi.dev.realestate.services.interfaces.INotificationService;
import java.util.List;

 @RestController
@RequestMapping("/notifications")
public class NotificationController {
 @Autowired
private INotificationService inotificationService;

     @PostMapping("/add")
     public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
         Notification savedNotification = inotificationService.createNotification(notification);
         return ResponseEntity.ok(savedNotification);
     }


    @GetMapping("/user/{userId}")
     public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable int userId) {
         List<Notification> notifications = inotificationService.getNotificationsByRecipient(userId);
         return ResponseEntity.ok(notifications);
     }

     @PutMapping("/{notificationId}/read")
     public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long notificationId) {
         inotificationService.markNotificationAsRead(notificationId);
         return ResponseEntity.noContent().build();
     }

     @GetMapping("/unread-count")
     public ResponseEntity<Long> countUnreadNotifications(@RequestParam("recipient") int recipient) {
        long unreadCount = inotificationService.countUnreadNotifications(recipient);
         return ResponseEntity.ok(unreadCount);
    }
 }

