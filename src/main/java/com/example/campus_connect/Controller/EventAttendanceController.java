package com.example.campus_connect.Controller;

// import com.example.club_connect.Service.EventAttendanceService;
// import com.example.club_connect.Entity.EventAttendanceEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;

import java.util.*;

import com.example.campus_connect.Entity.EventAttendanceEntity;
import com.example.campus_connect.Service.EventAttendanceService;

@Controller
@RequestMapping("/api/event-attendance")
public class EventAttendanceController {

    @Autowired
    private EventAttendanceService eventAttendanceService;

    @GetMapping("/all")
    public ResponseEntity<List<EventAttendanceEntity>> getAllEventAttendances() {
        List<EventAttendanceEntity> eventAttendances = eventAttendanceService.getAllEventAttendances();
        return new ResponseEntity<>(eventAttendances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventAttendanceEntity> getEventAttendanceById(@PathVariable Integer id) {
        return eventAttendanceService.getEventAttendanceById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<EventAttendanceEntity> createEventAttendance(
            @RequestBody EventAttendanceEntity eventAttendance) {
        return eventAttendanceService.createEventAttendance(eventAttendance);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventAttendanceEntity> updateEventAttendance(@PathVariable Integer id,
            @RequestBody EventAttendanceEntity eventAttendance) {
        return eventAttendanceService.updateEventAttendance(id, eventAttendance);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EventAttendanceEntity> deleteEventAttendance(@PathVariable Integer id) {
        return eventAttendanceService.deleteEventAttendance(id);
    }

}
