package com.example.club_connect.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.club_connect.Entity.EventAttendanceEntity;
import com.example.club_connect.Repository.EventAttendanceRepository;

import java.util.*;

@Service
public class EventAttendanceService {

    @Autowired
    private EventAttendanceRepository eventAttendanceRepository;

    public List<EventAttendanceEntity> getAllEventAttendances() {
        List<EventAttendanceEntity> eventAttendanceList = eventAttendanceRepository.findAll();
        return eventAttendanceList;
    }

    public ResponseEntity<EventAttendanceEntity> getEventAttendanceById(Integer id) {
        EventAttendanceEntity eventAttendance = eventAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event attendance not found with id: " + id));
        return ResponseEntity.ok(eventAttendance);
    }

    public ResponseEntity<EventAttendanceEntity> createEventAttendance(EventAttendanceEntity eventAttendance) {
        boolean isExist = eventAttendanceRepository.existsById(eventAttendance.getId());
        if (isExist) {
            throw new RuntimeException("Event attendance already exists with id: " + eventAttendance.getId());
        } else {
            EventAttendanceEntity createdEventAttendance = eventAttendanceRepository.save(eventAttendance);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEventAttendance);
        }
    }

    public ResponseEntity<EventAttendanceEntity> updateEventAttendance(Integer id,
            EventAttendanceEntity eventAttendance) {
        EventAttendanceEntity existingEventAttendance = eventAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event attendance not found with id: " + id));

        if (eventAttendance.getAttended() != null) {
            existingEventAttendance.setAttended(eventAttendance.getAttended());
        }
        if (eventAttendance.getCheckInTime() != null) {
            existingEventAttendance.setCheckInTime(eventAttendance.getCheckInTime());
        }

        final EventAttendanceEntity updatedEventAttendance = eventAttendanceRepository.save(existingEventAttendance);
        return ResponseEntity.ok(updatedEventAttendance);
    }

    public ResponseEntity<EventAttendanceEntity> deleteEventAttendance(Integer id) {
        EventAttendanceEntity eventAttendance = eventAttendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event attendance not found with id: " + id));
        eventAttendanceRepository.delete(eventAttendance);
        return ResponseEntity.ok().build();
    }

}
