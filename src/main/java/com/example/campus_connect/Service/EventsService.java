package com.example.campus_connect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.campus_connect.Entity.EventsEntity;
import com.example.campus_connect.Entity.UsersEntity;
import com.example.campus_connect.Repository.EventsRepository;
import com.example.campus_connect.Repository.UsersRepository;
import com.example.campus_connect.Entity.NotificationEntity;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private NotificationService notificationService;

    public List<EventsEntity> getAllEvents() {
        List<EventsEntity> eventsList = eventsRepository.findAll();
        return eventsList;
    }

    public ResponseEntity<EventsEntity> getEventById(Integer id) {
        EventsEntity event = eventsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return ResponseEntity.ok(event);
    }

    public ResponseEntity<EventsEntity> createEvent(EventsEntity event) {
        EventsEntity createdEvent = eventsRepository.save(event);
        // Notify OSA Admin(s) that a new event is pending approval
        List<UsersEntity> osaAdmins = usersRepository.findByUserType("OSA_Admin");
        for (UsersEntity admin : osaAdmins) {
            NotificationEntity notification = new NotificationEntity();
            notification.setRecipient(admin); // or appropriate recipient
            notification.setReferenceId(createdEvent.getId());
            notification.setReferenceType("EVENT");
            notification.setNotificationType("PENDING");
            notification.setMessage("A new event requires your approval.");

            notificationService.sendNotification(notification);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    public ResponseEntity<EventsEntity> updateEvent(Integer id, EventsEntity event) {
        EventsEntity existingEvent = eventsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        String previousStatus = existingEvent.getStatus();

        if (event.getEventName() != null) {
            existingEvent.setEventName(event.getEventName());
        }
        if (event.getEventDate() != null) {
            existingEvent.setEventDate(event.getEventDate());
        }
        if (event.getLocation() != null) {
            existingEvent.setLocation(event.getLocation());
        }
        if (event.getDescription() != null) {
            existingEvent.setDescription(event.getDescription());
        }
        if (event.getStatus() != null) {
            existingEvent.setStatus(event.getStatus());
        }

        EventsEntity updatedEvent = eventsRepository.save(existingEvent);

        // If status changed and is either Approved or Rejected, send notification
        String newStatus = existingEvent.getStatus();
        if (newStatus != null && !newStatus.equals(previousStatus)) {
            UsersEntity officer = existingEvent.getClub().getPresident();
            if (officer != null && ("Approved".equalsIgnoreCase(newStatus) || "Rejected".equalsIgnoreCase(newStatus))) {
                NotificationEntity notification = new NotificationEntity();
                notification.setRecipient(officer);
                notification.setReferenceId(existingEvent.getId());
                notification.setReferenceType("EVENT");
                notification.setNotificationType("STATUS_UPDATE");
                notification.setMessage("Your event has been " + newStatus.toLowerCase() + ".");
                notificationService.sendNotification(notification);
            }
        }
        return ResponseEntity.ok(updatedEvent);
    }

    public ResponseEntity<EventsEntity> deleteEvent(Integer id) {
        EventsEntity event = eventsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        eventsRepository.delete(event);
        return ResponseEntity.ok().build();
    }
}
