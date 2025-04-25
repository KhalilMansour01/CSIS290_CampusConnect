package com.example.campus_connect.Modules.Events;

// import com.example.club_connect.Service.EventsService;
// import com.example.club_connect.Entity.EventsEntity;

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

@Controller
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/all")
    public ResponseEntity<List<EventsEntity>> getAllEvents() {
        List<EventsEntity> events = eventsService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventsEntity> getEventById(@PathVariable Integer id) {
        return eventsService.getEventById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<EventsEntity> createEvent(@RequestBody EventsEntity event) {
        return eventsService.createEvent(event);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventsEntity> updateEvent(@PathVariable Integer id, @RequestBody EventsEntity event) {
        return eventsService.updateEvent(id, event);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EventsEntity> deleteEvent(@PathVariable Integer id) {
        return eventsService.deleteEvent(id);
    }
}
