package com.example.campus_connect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.campus_connect.Entity.EventsEntity;
import com.example.campus_connect.Repository.EventsRepository;



@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

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
        boolean isExist = eventsRepository.existsById(event.getId());
        if (isExist) {
            throw new RuntimeException("Event already exists with id: " + event.getId());
        } else {
            EventsEntity createdEvent = eventsRepository.save(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
        }
    }
    public ResponseEntity<EventsEntity> updateEvent(Integer id, EventsEntity event) {
        EventsEntity existingEvent = eventsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

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

        final EventsEntity updatedEvent = eventsRepository.save(existingEvent);
        return ResponseEntity.ok(updatedEvent);
    }

    public ResponseEntity<EventsEntity> deleteEvent(Integer id) {
        EventsEntity event = eventsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        eventsRepository.delete(event);
        return ResponseEntity.ok().build();
    }
}
